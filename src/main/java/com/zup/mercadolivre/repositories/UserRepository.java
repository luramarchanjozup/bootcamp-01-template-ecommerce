package com.zup.mercadolivre.repositories;

import java.util.Optional;

import com.zup.mercadolivre.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
