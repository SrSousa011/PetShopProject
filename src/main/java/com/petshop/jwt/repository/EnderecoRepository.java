package com.petshop.repository;

import com.petshop.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
    Endereco findById(long id);
}
