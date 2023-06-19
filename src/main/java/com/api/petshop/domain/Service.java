package com.api.petshop.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_atendimento")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long atendimento_id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Service(Long atendimento_id, Date date, Pet pet) {
        this.atendimento_id = atendimento_id;
        this.date = date;
        this.pet = pet;
    }

    public Service() {

    }

    public Long getAtendimento_id() {
        return atendimento_id;
    }

    public void setAtendimento_id(Long id) {
        this.atendimento_id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
