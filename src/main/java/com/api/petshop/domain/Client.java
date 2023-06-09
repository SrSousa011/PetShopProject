package com.api.petshop.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_clients")
public class Client extends RepresentationModel<Client> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    private String name;


    @Column(name = "cpf")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_register")
    private LocalDate date_register;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Set<Address> address = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Set<Contact> contact = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Set<Pet> pet = new HashSet<>();

}




