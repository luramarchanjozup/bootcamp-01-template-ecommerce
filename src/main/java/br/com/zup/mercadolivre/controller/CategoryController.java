package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.dto.request.CategoryRequestDTO;
import br.com.zup.mercadolivre.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Category> createCategory (@RequestBody @Valid CategoryRequestDTO request) {
        Category newCategory = request.toModel(entityManager);
        entityManager.persist(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
