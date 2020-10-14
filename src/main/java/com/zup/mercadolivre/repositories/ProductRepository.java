package com.zup.mercadolivre.repositories;

import java.util.List;
import java.util.Optional;

import com.zup.mercadolivre.model.Product;
import com.zup.mercadolivre.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByOwner(User owner);
}
