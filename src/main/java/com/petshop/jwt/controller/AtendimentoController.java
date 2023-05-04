package com.petshop.controller;


import com.petshop.configuration.swagger.AuthorizationInfo;
import com.petshop.domain.Atendimento;
import com.petshop.repository.AtendimentoRepository;
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
public class AtendimentoController {


    @Autowired
    AtendimentoRepository atendimentoRepo;

    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de atendimentos")
    @GetMapping(value="/atendimentos")
    public List<Atendimento> listaAtendimentos(){
        return atendimentoRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna um atendimento unico")
    @GetMapping("/atendimento/{id}")
    public Atendimento listaAtendimentoUnico(@PathVariable(value="id") long id){
        return atendimentoRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona um atendimento")
    @PostMapping(value="/atendimento")
    public Atendimento salvaAtendimento(@RequestBody @Validated Atendimento atendimento) {
        return atendimentoRepo.save(atendimento);
    }
    @AuthorizationInfo
    @ApiOperation(value="Deleta um atendimento")
    @DeleteMapping(value="/atendimento/{id}")
    public void deletaatendimento(@PathVariable(value="id") long id) { atendimentoRepo.deleteById(id);  }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um atendimento")
    @PutMapping(value="/atendimento")
    public Atendimento atualizaAtendimento(@RequestBody @Validated Atendimento atendimento) {
        return atendimentoRepo.save(atendimento);
    }


}
