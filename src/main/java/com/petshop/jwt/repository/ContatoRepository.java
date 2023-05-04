package com.petshop.repository;

import com.petshop.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {
    Contato findById(long id);

}
