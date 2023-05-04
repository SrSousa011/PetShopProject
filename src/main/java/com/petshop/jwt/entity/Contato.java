package com.petshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tbl_contato")
@Table(name = "tbl_contato")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long contatoId;

    @Column(name = "Id_cliente")
    private long clienteId;


    @Column(name = "tipo")
    private String tipo;


    @Column(name = "valor")
    private double valor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_contato_endereco",foreignKey = @ForeignKey(name = "fk_contato_endereco"))
    @JsonManagedReference
    private List<Endereco> enderecoList = new ArrayList<>();
    public void addEndereco(Endereco endereco) {
        enderecoList.add(endereco);
    }
    public void removeEndereco(Endereco endereco) {
        enderecoList.remove(endereco);
    }

}
