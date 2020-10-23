package com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.OpinionForm;
import com.zup.mercadolivre.model.products.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the {@link ProductOpinion} creation.
 * 
 * <p>Receives a {@link OpinionForm} with the proper information
 * for a new {@link ProductOpinion}. The Opinion is injected in
 * a existing Product and then the {@link EntityManager}
 * saves the updated Product to the database.
 * 
 * @author Matheus
 */
@RestController
//3
public class OpinionController {
    
    @Autowired
    private EntityManager manager;

    @PutMapping("/product/{id}/opinion")
    @Transactional
    //1
    public ResponseEntity<?> createProductOpinion(@PathVariable Long id, @RequestBody @Valid /*1*/ OpinionForm form) {
        //1
        Product product = manager.find(Product.class, id);
        product.setOpinions(form.toOpinion(manager, product));

        manager.merge(product);

        return ResponseEntity.ok().body(product.toDto());
    }
}
