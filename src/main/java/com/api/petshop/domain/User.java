package com.api.petshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user")
public class User extends RepresentationModel<User> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long user_id;
    private String username;
    private String password;
    private String cpf;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
