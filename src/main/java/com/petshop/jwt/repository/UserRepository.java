package com.petshop.jwt.repository;

import com.petshop.jwt.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findById(long id);

    boolean existsById(long id);
    Optional<User> findByUsername(String username);

    @Override
    Page<User> findAll(Pageable pageable);

    boolean existsByCpf(String cpf);
    boolean existsByUsername(String username);


}
