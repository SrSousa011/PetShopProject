package com.api.petshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long pet_id;

    private String name;

    @ManyToOne
    private Client owner;

    @ManyToOne
    @JoinColumn(name = "race_id_fk")
    private Race race;

    @OneToMany(mappedBy = "pet")
    private List<Service> services;

    public Long getPet_id() {
        return pet_id;
    }

    public void setPet_id(Long id) {
        this.pet_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client dono) {
        this.owner = dono;
    }

    public Race getRaca() {
        return race;
    }

    public void setRaca(Race race) {
        this.race = race;
    }

    public List<Service> getAtendimentos() {
        return services;
    }

    public void setAtendimentos(List<Service> services) {
        this.services = services;
    }
}


