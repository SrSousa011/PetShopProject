package com.petshop.repository;

import com.petshop.domain.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pets,Long> {

    Pets findById(long id);

}
