package com.api.petshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_contact")
public class Contact extends RepresentationModel<Contact> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    private String email;

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long id) {
        this.contact_id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client cliente) {
        this.client = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk")
    private Client client;
}



