package com.zup.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.ProductForm;
import com.zup.mercadolivre.model.products.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Handles the {@link Product} creation.
 * 
 * <p>Receives a {@link ProductForm} with the proper information
 * for a new {@link Product}. The {@link EntityManager}, then,
 * saves the new Product to the database.
 * 
 * @author Matheus
 */
@RestController
@RequestMapping("/product")
//3
public class NewProductController {
    
    @Autowired
    private EntityManager manager;

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<?> createProduct(@RequestBody @Valid /*1*/ ProductForm form, UriComponentsBuilder uriBuilder) {
        //1
        Product product = form.toProduct(manager);
        manager.persist(product);

        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product.toDto());
    }
}
