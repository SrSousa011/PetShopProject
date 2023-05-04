package com.petshop.controller;


import com.petshop.configuration.swagger.AuthorizationInfo;
import com.petshop.domain.Contato;
import com.petshop.repository.ContatoRepository;
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
public class ContatoController {


    @Autowired
    ContatoRepository contatoRepo;

    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de contatos")
    @GetMapping(value="/contatos")
    public List<Contato> listaContatos(){
        return contatoRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna um contato unico")
    @GetMapping("/contato/{id}")
    public Contato listaContatoUnico(@PathVariable(value="id") long id){
        return contatoRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona um contato")
    @PostMapping(value="/contato")
    public Contato salvaContato(@RequestBody @Validated Contato contato) {
        return contatoRepo.save(contato);
    }
    @AuthorizationInfo
    @ApiOperation(value="Deleta um contato")
    @DeleteMapping(value="/contato/{id}")
    public void deletaContato(@PathVariable(value="id") long id) { contatoRepo.deleteById(id);  }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um contato")
    @PutMapping(value="/contato")
    public Contato atualizaContato(@RequestBody @Validated Contato contato) {
        return contatoRepo.save(contato);
    }


}
