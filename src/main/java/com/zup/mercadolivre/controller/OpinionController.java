package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.OpinionForm;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.repositories.ProductRepository;
import com.zup.mercadolivre.repositories.UserRepository;
import com.zup.mercadolivre.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpinionController {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    
    @PutMapping("/product/{id}/opinion")
    public ResponseEntity<?> createProductOpinion(@PathVariable Long id, @RequestBody @Valid OpinionForm form) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found"));
        User loggedUser = userRepository.findByEmail(UserService.authenticated().getUsername()).orElseThrow(
            () -> new IllegalStateException("User not found or it's not logged in properly"));
        product.checkOwnershipTrue(loggedUser.getEmail(), "The product owner can't post a opinion");

        product.setOpinions(form.toOpinion(product, loggedUser));
        productRepository.save(product);

        return ResponseEntity.ok().body(product.toDto());
    }
}
