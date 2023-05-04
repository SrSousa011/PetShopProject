package com.petshop.controller;


import com.petshop.configuration.swagger.AuthorizationInfo;
import com.petshop.domain.Cliente;
import com.petshop.repository.ClienteRepository;
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
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepo;

    @AuthorizationInfo
    @ApiOperation(value="Retorna uma lista de clientes")
    @GetMapping(value="/clientes")
    public List<Cliente> listaClientes(){
        return clienteRepo.findAll();
    }
    @AuthorizationInfo
    @ApiOperation(value="Retorna um cliente unico")
    @GetMapping("/cliente/{id}")
    public Cliente listaClienteUnico(@PathVariable(value="id") long id){
        return clienteRepo.findById(id);
    }
    @AuthorizationInfo
    @ApiOperation(value="Adiciona um cliente")
    @PostMapping(value="/cliente")
    public Cliente salvaCliente(@RequestBody @Validated Cliente cliente) {
        return clienteRepo.save(cliente);
    }
    @AuthorizationInfo
    @ApiOperation(value="Deleta um cliente")
    @DeleteMapping(value="/cliente/{id}")
    public void deletaCliente(@PathVariable(value="id") long id) { clienteRepo.deleteById(id);  }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um cliente")
    @PutMapping(value="/cliente")
    public Cliente atualizaCliente(@RequestBody @Validated Cliente cliente) {
        return clienteRepo.save(cliente);
    }


}
