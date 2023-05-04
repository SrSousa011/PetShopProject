package com.petshop.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tbl_atendimento")
@Table(name = "tbl_atendimento")
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long atendimentoId;

    @Column(name = "petId")
    private long petId;


    @Column(name = "descricaoAtendimento")
    private String descricaoAtendimento;


    @Column(name = "valor")
    private double valor;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

/*
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_atendimento_pets", foreignKey = @ForeignKey(name = "fk_atendimento_pets"))
    @JsonBackReference
    private Pets pets; */

}