package com.api.petshop.controllers;


import com.api.petshop.domain.Client;
import com.api.petshop.dtos.ClientRecordDto;
import com.api.petshop.repositories.ClientRepository;
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
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API REST Pets")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @AuthorizationInfo
    @GetMapping("/clients")
    @ApiOperation(value="Obtém todos os clientes")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        if(!clientList.isEmpty()) {
            for(Client client : clientList) {
                long id = client.getClient_id();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }
    @AuthorizationInfo
    @GetMapping("/client/{id}")
    @ApiOperation(value = "Recebe um cliente por ID")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id") long id){
        Optional<Client> client0 = clientRepository.findById(id);
        if(client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        client0.get().add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("Client List"));
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    @AuthorizationInfo
    @PostMapping("/clients")
    @ApiOperation(value = "Cria um novo cliente")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientRecordDto clientRecordDto) {
        var client = new Client();
        BeanUtils.copyProperties(clientRecordDto, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
    }



    @AuthorizationInfo
    @DeleteMapping("/client/{id}")
    @ApiOperation(value = "Exclui um cliente por ID")
    public ResponseEntity<Object> deleteAddress(@PathVariable(value="id") long id) {
        Optional<Client> client0 = clientRepository.findById(id);
        if(client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientRepository.delete(client0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }
    @AuthorizationInfo
    @PutMapping("/client/{id}")
    @ApiOperation(value = "Atualiza um cliente por ID")
    public ResponseEntity<Object> updateAddress(@PathVariable(value="id") long id,
                                                @RequestBody @Valid ClientRecordDto clientRecordDto) {
        Optional<Client> client0 = clientRepository.findById(id);
        if(client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var client = client0.get();
        BeanUtils.copyProperties(clientRecordDto, client);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(client));
    }
}
