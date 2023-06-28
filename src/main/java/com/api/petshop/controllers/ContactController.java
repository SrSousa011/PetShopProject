package com.api.petshop.controllers;

import com.api.petshop.domain.Client;
import com.api.petshop.domain.Contact;
import com.api.petshop.dtos.ContactRecordDto;
import com.api.petshop.repositories.ContactRepository;
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
@RequestMapping("contacts")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API REST Pets")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @AuthorizationInfo
    @GetMapping
    @ApiOperation(value="Obtém todos os contatos")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contactList = contactRepository.findAll();
        if(!contactList.isEmpty()) {
            for(Contact contact : contactList) {
                long id = contact.getContact_id();
                contact.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactList);
    }
    @AuthorizationInfo
    @GetMapping("/{id}")
    @ApiOperation(value = "Recebe um contato por ID")
    public ResponseEntity<Object> getOneContact(@PathVariable(value="id") long id){
        Optional<Contact> contact0 = contactRepository.findById(id);
        if(contact0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacts not found.");
        }
        contact0.get().add(linkTo(methodOn(ContactController.class).getAllContacts()).withRel("Contacts List"));
        return ResponseEntity.status(HttpStatus.OK).body(contact0.get());
    }

    @AuthorizationInfo
    @PostMapping
    @ApiOperation(value = "Cria um novo contato")
    public ResponseEntity<Contact> saveContact(@RequestBody @Valid ContactRecordDto contactRecordDto) {
        var contact = new Contact();
        BeanUtils.copyProperties(contactRecordDto, contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
    }



    @AuthorizationInfo
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um contato por ID")
    public ResponseEntity<Object> deleteContact(@PathVariable(value="id") long id) {
        Optional<Contact> contact0 = contactRepository.findById(id);
        if(contact0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        contactRepository.delete(contact0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }
    @AuthorizationInfo
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um contato por ID")
    public ResponseEntity<Object> updateContact(@PathVariable(value="id") long id,
                                                @RequestBody @Valid ContactRecordDto contactRecordDto) {
        Optional<Contact> contact0 = contactRepository.findById(id);
        if(contact0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        var contact = contact0.get();
        BeanUtils.copyProperties(contactRecordDto, contact);
        return ResponseEntity.status(HttpStatus.OK).body(contactRepository.save(contact));
    }
}
