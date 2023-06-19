package com.api.petshop.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    private String email;


    public Contact(Long contact_id, String email, Client cliente) {
        this.contact_id = contact_id;
        this.email = email;
        this.cliente = cliente;
    }

    public Contact() {

    }

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

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client cliente;
}



