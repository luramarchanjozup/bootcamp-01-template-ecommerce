package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//6
public class ProductListingController {
    
    @Autowired
    //1
    private ProductRepository productRepository;

    @GetMapping("/product/{id}/question")
    //1
    public ResponseEntity<?> listQuestions(@PathVariable Long id) {
        return ResponseEntity.ok().body(
            getProductRepo(id).questionToDto()
        );
    }

    @GetMapping("/product/{id}/opinion")
    //1
    public ResponseEntity<?> listOpinions(@PathVariable Long id) {
        return ResponseEntity.ok().body(
            getProductRepo(id).opinionsDto()
        );
    }

    @GetMapping("/product/{id}")
    //1
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(
            getProductRepo(id).toDto()
        );
    }

    //1
    private Product getProductRepo(Long id) {
        return productRepository.findById(id).orElseThrow(/*1*/() -> new IllegalStateException("Product not found"));
    }
}
