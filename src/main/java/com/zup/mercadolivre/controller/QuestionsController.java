package com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.QuestionForm;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductQuestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles {@link ProductQuestions} creation.
 * 
 * @author Matheus Santos
 */
@RestController
//4
public class QuestionsController {
    
    @Autowired
    private EntityManager manager;
    //@Autowired
    //1
    //private MailService mailService;
    
    @PutMapping("/product/{id}/question")
    @Transactional
    //1
    public ResponseEntity<?> createQuestion(@PathVariable Long id, @RequestBody @Valid /*1*/ QuestionForm form ) {
        //1
        Product product = manager.find(Product.class, id);

        product.setQuestions(form.toQuestion(manager, product));
        manager.persist(product);
        // Uncomment for mail functionality in production
        //mailService.sendEmailToSeller(product.getOwner().getEmail(), product.getName());

        return ResponseEntity.ok().body(product.toDto());
    }
}
