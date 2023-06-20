package com.api.petshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_client")
public class Client extends RepresentationModel<Client> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    private String name;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long id) {
        this.client_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    @Column(name = "address_id")
    private Long addressId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Contact> contacts;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Pet> pets;
}




