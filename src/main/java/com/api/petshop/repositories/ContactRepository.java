package com.api.petshop.repositories;

import com.api.petshop.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Client, Long> {
}
