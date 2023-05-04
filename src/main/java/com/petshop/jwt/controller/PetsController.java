package com.petshop.controller;

import com.petshop.configuration.swagger.AuthorizationInfo;
import com.petshop.domain.Pets;
import com.petshop.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
@Api(value="API REST Pets")
public class PetsController {

    @Autowired
    PetRepository petRepo;
    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de Pets")
    @GetMapping(value="/pets")
    public List<Pets> listaPets(){
        return petRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna um pet unico")
    @GetMapping("/pet/{id}")
    public Pets listaPetUnico(@PathVariable(value="id") long id){
        return petRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona um pet")
    @PostMapping(value="/pet")
    public Pets salvaPet(@RequestBody @Validated Pets pets) {
        return petRepo.save(pets);
    }
    @AuthorizationInfo
    @ApiOperation(value="Deleta um pet")
    @DeleteMapping(value="/pet/{id}")
    public void deletaPet(@PathVariable(value="id") long id) {
        petRepo.deleteById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um pet")
    @PutMapping(value="/pet")
    public Pets atualizaPet(@RequestBody @Validated Pets pets) {
        return petRepo.save(pets);
    }

}
