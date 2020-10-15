package com.zup.mercadolivre.repositories;

import java.util.List;
import java.util.Optional;

import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductQuestions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<ProductQuestions, Long> {
    Optional<List<ProductQuestions>> findByProduct(Product product);
}
