package com.api.petshop.controllers;

import com.api.petshop.domain.Race;
import com.api.petshop.dtos.RaceRecordDto;
import com.api.petshop.repositories.RaceRepository;
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
@RequestMapping("races")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API REST Races")
public class RaceController {
    @Autowired
    RaceRepository raceRepository;

    @GetMapping
    @AuthorizationInfo
    @ApiOperation(value = "Retorna uma lista de raças")
    public ResponseEntity<List<Race>> getAllRaces() {
        List<Race> raceList = raceRepository.findAll();
        if (!raceList.isEmpty()) {
            for (Race race : raceList) {
                long id = race.getRace_id();
                race.add(linkTo(methodOn(RaceController.class).getOneRace(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(raceList);
    }

    @GetMapping("/{id}")
    @AuthorizationInfo
    @ApiOperation(value = "Retorna uma raça pelo ID")
    public ResponseEntity<Object> getOneRace(@PathVariable(value = "id") long id) {
        Optional<Race> race0 = raceRepository.findById(id);
        if (race0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race not found.");
        }
        race0.get().add(linkTo(methodOn(RaceController.class).getAllRaces()).withRel("Races List"));
        return ResponseEntity.status(HttpStatus.OK).body(race0.get());
    }

    @PostMapping
    @AuthorizationInfo
    @ApiOperation(value = "Cria uma nova raça")
    public ResponseEntity<Race> saveRace(@RequestBody @Valid RaceRecordDto raceRecordDto) {
        var race = new Race();
        BeanUtils.copyProperties(raceRecordDto, race);
        return ResponseEntity.status(HttpStatus.CREATED).body(raceRepository.save(race));
    }

    @DeleteMapping("/{id}")
    @AuthorizationInfo
    @ApiOperation(value = "Exclui uma raça")
    public ResponseEntity<Object> deleteRace(@PathVariable(value = "id") long id) {
        Optional<Race> race0 = raceRepository.findById(id);
        if (race0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race not found.");
        }
        raceRepository.delete(race0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Race deleted successfully.");
    }

    @PutMapping("/{id}")
    @AuthorizationInfo
    @ApiOperation(value = "Atualiza uma raça")
    public ResponseEntity<Object> updateRace(@PathVariable(value = "id") long id,
                                             @RequestBody @Valid RaceRecordDto raceRecordDto) {
        Optional<Race> race0 = raceRepository.findById(id);
        if (race0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race not found.");
        }
        var race = race0.get();
        BeanUtils.copyProperties(raceRecordDto, race);
        return ResponseEntity.status(HttpStatus.OK).body(raceRepository.save(race));
    }
}
