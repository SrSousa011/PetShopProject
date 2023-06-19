package com.api.petshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    private String email;


    public Contact(Long contact_id, String email, Client client) {
        this.contact_id = contact_id;
        this.email = email;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client cliente) {
        this.client = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client;
}



