package com.petshop.jwt.dto;

import com.petshop.jwt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    //@NotBlank
    //@Size(min = 3, max = 255)
    private String username;

    //@NotBlank
    //@Size(min = 11, max = 11)
    private String cpf;

    //@NotBlank
    //@Size(min = 6, max = 255)
    private String password;

    public UserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setCpf(cpf);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user);
    }


    public static List<UserDto> fromEntityList(List<User> userList) {
        return userList.stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }



}