package com.api.petshop.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long race_id;

    private String name;

    @OneToMany(mappedBy = "race")
    private List<Pet> pets;

    public Race(Long race_id, String name, List<Pet> pets) {
        this.race_id = race_id;
        this.name = name;
        this.pets = pets;
    }

    public Race() {

    }

    public Long getRace_id() {
        return race_id;
    }

    public void setRace_id(Long id) {
        this.race_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}

