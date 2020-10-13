package com.zup.mercadolivre.repositories;

import java.util.Optional;

import com.zup.mercadolivre.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
