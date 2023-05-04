package com.petshop.controller;


import com.petshop.configuration.swagger.AuthorizationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.petshop.domain.Endereco;
import com.petshop.repository.EnderecoRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
@Api(value="API REST Pets")
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepo;
    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de endereços")
    @GetMapping(value="/enderecos")
    public List<Endereco> listaEnderecos(){
        return enderecoRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna um endereço unico")
    @GetMapping("/endereco/{id}")
    public Endereco listaEnderecoUnico(@PathVariable(value="id") long id){
        return enderecoRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona um endereço")
    @PostMapping(value="/endereco")
    public Endereco salvaPet(@RequestBody @Validated Endereco endereco) {
        return enderecoRepo.save(endereco);
    }
    @AuthorizationInfo
    @ApiOperation(value="Deleta um endereco")
    @DeleteMapping(value="/endereco/{id}")
    public void deletaEndereco(@PathVariable(value="id") long id) {
        enderecoRepo.deleteById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um endereço")
    @PutMapping(value="/endereco")
    public Endereco atualizaEndereco(@RequestBody @Validated Endereco endereco) {
        return enderecoRepo.save(endereco);
    }

}
