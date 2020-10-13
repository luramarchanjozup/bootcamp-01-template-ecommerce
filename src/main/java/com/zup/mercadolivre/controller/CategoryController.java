package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.CategoryForm;
import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.repositories.CategoryRepository;
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

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CheckDuplicatedCategory checkDuplicatedCategory;
    
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(checkDuplicatedCategory);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryForm form) {
        Category category = new Category(form.getName(), null);
        if (!form.getParentCategory().isBlank()) {
            category.setParentCategory(categoryRepository.findByName(form.getParentCategory()).orElseThrow(
                                        () -> new IllegalStateException("Parent category not found")));
        }
        
        categoryRepository.save(category);
        
        return ResponseEntity.ok().build();
    }
}
