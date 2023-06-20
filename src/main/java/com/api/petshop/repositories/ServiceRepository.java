package com.api.petshop.repositories;

import com.api.petshop.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
