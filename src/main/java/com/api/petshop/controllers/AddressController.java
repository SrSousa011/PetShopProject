package com.api.petshop.controllers;

import com.api.petshop.domain.Address;
import com.api.petshop.domain.User;
import com.api.petshop.dtos.AddressRecordDto;
import com.api.petshop.dtos.UserRecordDto;
import com.api.petshop.repositories.AddressRepository;
import io.swagger.annotations.Api;
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
@Api(value = "API REST Pets")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAllAddresses(){
        List<Address> addressList = addressRepository.findAll();
        if(!addressList.isEmpty()) {
            for(Address address : addressList) {
                long id = address.getAddress_Id();
                address.add(linkTo(methodOn(AddressController.class).getOneAddress(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(addressList);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Object> getOneAddress(@PathVariable(value="id") long id){
        Optional<Address> address0 = addressRepository.findById(id);
        if(address0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found.");
        }
        address0.get().add(linkTo(methodOn(AddressController.class).getAllAddresses()).withRel("Address List"));
        return ResponseEntity.status(HttpStatus.OK).body(address0.get());
    }


    @PostMapping("/addresses")
    public ResponseEntity<Address> saveAddress(@RequestBody @Valid AddressRecordDto addressRecordDto) {
        var address = new Address();
        BeanUtils.copyProperties(addressRecordDto, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressRepository.save(address));
    }


    @DeleteMapping("/address/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable(value="id") long id) {
        Optional<Address> address0 = addressRepository.findById(id);
        if(address0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found.");
        }
        addressRepository.delete(address0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted successfully.");
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable(value="id") long id,
                                                @RequestBody @Valid AddressRecordDto addressRecordDto) {
        Optional<Address> address0 = addressRepository.findById(id);
        if(address0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found.");
        }
        var address = address0.get();
        BeanUtils.copyProperties(addressRecordDto, address);
        return ResponseEntity.status(HttpStatus.OK).body(addressRepository.save(address));
    }
}
