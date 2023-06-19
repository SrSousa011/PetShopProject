package com.api.petshop.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long service_id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Service(Long service_id, Date date, Pet pet) {
        this.service_id = service_id;
        this.date = date;
        this.pet = pet;
    }

    public Service() {

    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long id) {
        this.service_id = id;
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
