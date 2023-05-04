package com.petshop.jwt.controller;

import com.petshop.jwt.dto.UserDto;
import com.petshop.jwt.entity.User;
import com.petshop.jwt.service.UserService;
import com.petshop.jwt.swagger.AuthorizationInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value="API REST Pets")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }
    @AuthorizationInfo
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @AuthorizationInfo
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
    @AuthorizationInfo
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @AuthorizationInfo
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/usuario/{id}")
    @ApiOperation(value="Retorna um usuário pelo ID")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            UserDto userDto = UserDto.fromEntity(optionalUser.get());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            String message = "User with ID " + id + " not Found.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @AuthorizationInfo
    @PreAuthorize("hasRole('User')")
    @GetMapping("/usuarios")
    @ApiOperation(value="Retorna todos os usuários")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> userList = userService.findAll();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<UserDto> userDtoList = UserDto.fromEntityList(userList);
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        }
    }
    @AuthorizationInfo
    @PostMapping("/usuario")
    @ApiOperation(value="Cria um novo usuário")
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {

        if (userService.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Conflict: Username is already registered!");
        }
        if (userService.existsByCpf(userDto.getCpf())) {
            throw new IllegalArgumentException("Conflict: CPF is already registered!");
        }
        User user = userService.create(userDto.toEntity());
        UserDto createdUserDto = UserDto.fromEntity(user);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    @AuthorizationInfo
    @ApiOperation(value="Atualiza um usuário")
    @PutMapping(value="/usuario/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") long id,
                                              @RequestBody @Validated UserDto userDto) {

        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = userOptional.get();

        //atualiza as informações do usuário
        user.setCpf(userDto.getCpf());
        user.setUsername(userDto.getUsername());
        user.setUsername(userDto.getPassword());

        userService.update(user);
        UserDto updatedUserDto = UserDto.fromEntity(user);
        return ResponseEntity.ok().body(updatedUserDto);
    }

    @AuthorizationInfo
    @ApiOperation(value="Deleta um usuário")
    @DeleteMapping(value="/usuario/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id ) {

        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.delete(userOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

}
