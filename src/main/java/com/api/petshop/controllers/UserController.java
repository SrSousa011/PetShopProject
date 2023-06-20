package com.api.petshop.controllers;

import com.api.petshop.domain.User;
import com.api.petshop.dtos.UserRecordDto;
import com.api.petshop.repositories.UserRepository;
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
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ApiOperation(value="Retorna uma lista de usuários")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userRepository.findAll();
        if(!userList.isEmpty()) {
            for(User user : userList) {
                long id = user.getUser_id();
                user.add(linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value="Retorna um usuário pelo ID")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") long id){
        Optional<User> user0 = userRepository.findById(id);
        if(user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        user0.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("Users List"));
        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }


    @PostMapping("/users")
    @ApiOperation(value="Cria um novo usuário")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }


    @DeleteMapping("/user/{id}")
    @ApiOperation(value="Exclui um usuário")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") long id) {
        Optional<User> user0 = userRepository.findById(id);
        if(user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/user/{id}")
    @ApiOperation(value="Atualiza um usuário")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") long id,
                                             @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<User> user0 = userRepository.findById(id);
        if(user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var user = user0.get();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }
}
