package com.api.petshop.controllers;

import com.api.petshop.domain.Service;
import com.api.petshop.dtos.ServiceRecordDto;
import com.api.petshop.repositories.ServiceRepository;
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
@RequestMapping("services")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API REST Pets")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @AuthorizationInfo
    @GetMapping
    @ApiOperation(value="Obtém todos os atendimentos")
    public ResponseEntity<List<Service>> getAllServices(){
        List<Service> serviceList = serviceRepository.findAll();
        if(!serviceList.isEmpty()) {
            for(Service service : serviceList) {
                long id = service.getService_id();
                service.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceList);
    }
    @AuthorizationInfo
    @GetMapping("/{id}")
    @ApiOperation(value = "Recebe um atendimento por ID")
    public ResponseEntity<Object> getOneService(@PathVariable(value="id") long id){
        Optional<Service> service0 = serviceRepository.findById(id);
        if(service0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
        }
        service0.get().add(linkTo(methodOn(ServiceController.class).getAllServices()).withRel("Services List"));
        return ResponseEntity.status(HttpStatus.OK).body(service0.get());
    }

    @AuthorizationInfo
    @PostMapping
    @ApiOperation(value = "Cria um novo atendimento")
    public ResponseEntity<Service> saveService(@RequestBody @Valid ServiceRecordDto serviceRecordDto) {
        var service = new Service();
        BeanUtils.copyProperties(serviceRecordDto, service);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceRepository.save(service));
    }



    @AuthorizationInfo
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um atendimento por ID")
    public ResponseEntity<Object> deleteService(@PathVariable(value="id") long id) {
        Optional<Service> service0 = serviceRepository.findById(id);
        if(service0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
        }
        serviceRepository.delete(service0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Service deleted successfully.");
    }
    @AuthorizationInfo
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um atendimento por ID")
    public ResponseEntity<Object> updateContact(@PathVariable(value="id") long id,
                                                @RequestBody @Valid ServiceRecordDto serviceRecordDto) {
        Optional<Service> service0 = serviceRepository.findById(id);
        if(service0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
        }
        var service = service0.get();
        BeanUtils.copyProperties(serviceRecordDto, service);
        return ResponseEntity.status(HttpStatus.OK).body(serviceRepository.save(service));
    }
}
