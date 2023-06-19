package com.api.petshop.repositories;

import com.api.petshop.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Pet, Long> {
}
