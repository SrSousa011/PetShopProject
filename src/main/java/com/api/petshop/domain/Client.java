package com.api.petshop.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Client(Long id, String name, Address address, List<Contact> contacts, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contacts = contacts;
        this.pets = pets;
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public Address getEndereco() {
        return address;
    }

    public void setEndereco(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contatos) {
        this.contacts = contatos;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Contact> contacts;


    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}

