package com.api.petshop.controllers;

import com.api.petshop.domain.Pet;
import com.api.petshop.dtos.PetRecordDto;
import com.api.petshop.repositories.PetRepository;
import com.api.petshop.swagger.AuthorizationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("pets")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API REST Pets")
public class PetController {
    @Autowired
    PetRepository petRepository;
    @GetMapping()
    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de pets")
    public ResponseEntity<List<Pet>> getAllPets(){
        List<Pet> petList = petRepository.findAll();
        if(!petList.isEmpty()) {
            for(Pet pet : petList) {
                long id = pet.getPet_id();
                pet.add(linkTo(methodOn(PetController.class).getOnePet(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(petList);
    }
    @AuthorizationInfo
    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um pet pelo ID")
    public ResponseEntity<Object> getOnePet(@PathVariable(value="id") long id){
        Optional<Pet> pet0 = petRepository.findById(id);
        if(pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found.");
        }
        pet0.get().add(linkTo(methodOn(PetController.class).getAllPets()).withRel("Pets List"));
        return ResponseEntity.status(HttpStatus.OK).body(pet0.get());
    }

    @AuthorizationInfo
    @PostMapping
    @ApiOperation(value="Cria um novo pet")
    public ResponseEntity<Pet> savePet(@RequestBody @Valid PetRecordDto petRecordDto) {
        var pet = new Pet();
        BeanUtils.copyProperties(petRecordDto, pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(petRepository.save(pet));
    }

    @AuthorizationInfo
    @DeleteMapping("/{id}")
    @ApiOperation(value="Exclui um usuário")
    public ResponseEntity<Object> deletePet(@PathVariable(value="id") long id) {
        Optional<Pet> pet0 = petRepository.findById(id);
        if(pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found.");
        }
        petRepository.delete(pet0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully.");
    }
    @AuthorizationInfo
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza um pet")
    public ResponseEntity<Object> updatePet(@PathVariable(value="id") long id,
                                             @RequestBody @Valid PetRecordDto petRecordDto) {
        Optional<Pet> pet0 = petRepository.findById(id);
        if(pet0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found.");
        }
        var pet = pet0.get();
        BeanUtils.copyProperties(petRecordDto, pet);
        return ResponseEntity.status(HttpStatus.OK).body(petRepository.save(pet));
    }
}
