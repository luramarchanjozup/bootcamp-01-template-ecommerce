package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.controller.dto.ProductDTO;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductOpinion;
import com.zup.mercadolivre.model.products.ProductQuestions;
import com.zup.mercadolivre.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles all the listing requests for a {@link Product}, 
 * {@link ProductQuestions} and {@link ProductOpinion}.
 * 
 * <p>They are separated for performance purposes. Previously
 * all three where inside the same {@link ProductDTO} class.
 * But the sizes of the Opinion and Questions lists was
 * taking a significant hit at the response time.
 * 
 * <p>A separate {@link ProductQuestions} and {@link ProductOpinion}
 * is actually better for a front-end application, it can use
 * separate lists to create a pagination feature, reducing the
 * time it takes to load the page.
 * 
 * @author Matheus Santos
 */
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
