package com.petshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tbl_cliente")
@Table(name = "tbl_cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long clientId;

    @Column(name = "nome")
    private String nome;

    //@CPF
    @Column(name = "cpf")
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_de_cadastro")
    private LocalDate data_Cadastro;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_endereco_cliente",foreignKey = @ForeignKey(name = "fk_endereco_cliente"))
    @JsonManagedReference
    private List<Endereco> enderecoList = new ArrayList<>();
    public void addEndereco(Endereco endereco) {
        enderecoList.add(endereco);
    }
    public void removeEndereco(Endereco endereco) {
        enderecoList.remove(endereco);
    }



}