package com.api.petshop.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Client(Long id, String nome, Address address, List<Contact> contatos, List<Pet> pets) {
        this.id = id;
        this.nome = nome;
        this.address = address;
        this.contatos = contatos;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Contact> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contact> contatos) {
        this.contatos = contatos;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contact> contatos;


    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}

