package com.petshop.repository;

import com.petshop.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento,Long> {
    Atendimento findById(long id);

}
