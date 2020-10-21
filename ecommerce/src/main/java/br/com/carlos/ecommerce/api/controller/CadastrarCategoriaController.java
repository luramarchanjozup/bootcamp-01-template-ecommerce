package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestCategoriaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class CadastrarCategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping(value="categorias")
    public ResponseEntity<?> adicionar(@Valid @RequestBody RequestCategoriaDto request) {
        var categoria = request.toEntity(manager);
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }
    
    
}
