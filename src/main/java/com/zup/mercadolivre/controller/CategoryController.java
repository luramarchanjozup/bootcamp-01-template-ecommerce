package com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.CategoryForm;
import com.zup.mercadolivre.services.validations.CheckDuplicatedCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the {@link Category} creation.
 * 
 * <p>Receives a {@link CategoryForm} with the proper information
 * for a new {@link Category}. The {@link EntityManager}, then,
 * saves the new Category to the database.
 * 
 * @author Matheus
 */
@RestController
@RequestMapping("/category")
//3
public class CategoryController {
    
    @Autowired
    private EntityManager manager;
    @Autowired
    //1
    private CheckDuplicatedCategory checkDuplicatedCategory;
    
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(checkDuplicatedCategory);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Transactional
    //1
    public ResponseEntity<?> createCategory(@RequestBody @Valid /*1*/ CategoryForm form) {
        manager.persist(form.toCategory(manager));
        
        return ResponseEntity.ok().build();
    }
}
