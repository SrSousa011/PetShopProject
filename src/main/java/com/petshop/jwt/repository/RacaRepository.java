package com.petshop.repository;

import com.petshop.domain.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends JpaRepository<Raca,Long> {
    Raca findById(long id);

}
