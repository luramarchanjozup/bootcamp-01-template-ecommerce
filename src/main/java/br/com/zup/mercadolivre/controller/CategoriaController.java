package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.dto.request.CategoriaRequestDTO;
import br.com.zup.mercadolivre.model.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> createCategory (@RequestBody @Valid CategoriaRequestDTO request) {
        Categoria novaCategoria = request.toModel(entityManager);
        entityManager.persist(novaCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }
}
