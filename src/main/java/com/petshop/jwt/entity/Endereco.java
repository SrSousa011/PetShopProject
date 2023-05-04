package com.petshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity(name = "tbl_endereco")
@Table(name = "tbl_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long enderecoId;


    private String cidade;


    private String bairro;


    private String logradouro;

    private String complemento;
    private long tag;

/*    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_cliente", foreignKey = @ForeignKey(name = "fk_endereco_cliente"))
    @JsonBackReference
    private Cliente cliente;

*/



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (enderecoId ^ (enderecoId >>> 32));
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
        result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
        result = prime * result + (int) (tag ^ (tag >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        if (enderecoId != other.enderecoId)
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (bairro == null) {
            if (other.bairro != null)
                return false;
        } else if (!bairro.equals(other.bairro))
            return false;
        if (logradouro == null) {
            if (other.logradouro != null)
                return false;
        } else if (!logradouro.equals(other.logradouro))
            return false;
        if (complemento == null) {
            if (other.complemento != null)
                return false;
        } else if (!complemento.equals(other.complemento))
            return false;
        if (tag != other.tag)
            return false;
        return true;
    }




}
