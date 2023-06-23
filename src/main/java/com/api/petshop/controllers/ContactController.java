package com.api.petshop.controllers;

import com.api.petshop.domain.Client;
import com.api.petshop.domain.Contact;
import com.api.petshop.dtos.ContactRecordDto;
import com.api.petshop.dtos.UserRecordDto;
import com.api.petshop.repositories.ContactRepository;
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
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contacts")
    @ApiOperation(value="Retorna uma lista de contatos")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contactList = contactRepository.findAll();
        if(!contactList.isEmpty()) {
            for(Contact contact : contactList) {
                long id = contact.getContact_id();
                contact.add(linkTo(methodOn(ContactController.class).getOneContact(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactList);
    }

    @GetMapping("/contact/{id}")
    @ApiOperation(value="Retorna um contato pelo ID")
    public ResponseEntity<Object> getOneContact(@PathVariable(value="id") long id){
        Optional<Contact> contact0 = contactRepository.findById(id);
        if(contact0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        contact0.get().add(linkTo(methodOn(ContactController.class).getAllContacts()).withRel("Contacts List"));
        return ResponseEntity.status(HttpStatus.OK).body(contact0.get());
    }


    @PostMapping("/contacts2")
    @ApiOperation(value="Cria um novo contato")
    public ResponseEntity<Contact> saveContact2(@RequestBody @Valid ContactRecordDto contactRecordDto) {
        var contact = new Contact();
        BeanUtils.copyProperties(contactRecordDto, contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
    }

    @PostMapping("/contacts")
    @ApiOperation(value="Cria um novo contato")
    public ResponseEntity<Contact> saveContact(@RequestBody @Valid ContactRecordDto contactRecordDto) {
        var contact = new Contact();
        BeanUtils.copyProperties(contactRecordDto, contact);

        // Verifique se o client_id está presente no DTO
        if (contactRecordDto.client() != null) {
            var client = new Client();
            client.setClient_id(contactRecordDto.client().client_id());
            contact.setClient(client);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
    }


    @DeleteMapping("/contact/{id}")
    @ApiOperation(value="Exclui um contato")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") long id) {
        Optional<Contact> contact0 = contactRepository.findById(id);
        if(contact0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
        }
        contactRepository.delete(contact0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contact deleted successfully.");
    }

    @PutMapping("/contact/{id}")
    @ApiOperation(value="Atualiza um contato")
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
