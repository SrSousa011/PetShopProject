package com.petshop.controller;

import com.petshop.configuration.swagger.AuthorizationInfo;
import com.petshop.domain.Raca;
import com.petshop.repository.RacaRepository;
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
public class RacaController {

    @Autowired
    RacaRepository racaRepo;
    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de Raças")
    @GetMapping(value="/racas")
    public List<Raca> listaRacas(){
        return racaRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna uma raça unica")
    @GetMapping("/raca/{id}")
    public Raca listaRacaUnico(@PathVariable(value="id") long id){
        return racaRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona uma raça")
    @PostMapping(value="/raca")
    public Raca salvaRaca(@RequestBody @Validated Raca raca) {
        return racaRepo.save(raca);
    }

    @AuthorizationInfo
    @ApiOperation(value="Deleta uma raça")
    @DeleteMapping(value="/raca/{id}")
    public void deletaPet(@PathVariable(value="id") long id) {
        racaRepo.deleteById(id);
    }

    @AuthorizationInfo
    @ApiOperation(value="Atualiza um raça")
    @PutMapping(value="/raca")
    public Raca atualizaPet(@RequestBody @Validated Raca raca) {
        return racaRepo.save(raca);
    }

}
