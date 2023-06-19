package com.api.petshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_endereco")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long address_id;

    private String street;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Address(Long address_id, String street, Client client) {
        this.address_id = address_id;
        this.street = street;
        this.client = client;
    }

    public Address() {

    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long id) {
        this.address_id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}


