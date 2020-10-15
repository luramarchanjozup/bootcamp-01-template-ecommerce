package com.zup.mercadolivre.categoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String cadastrarCategoria(@Valid @RequestBody NovaCategoriaRequest request) {
        Categoria novaCategoria = request.toModel(manager);
        manager.persist(novaCategoria);
        return novaCategoria.toString();
    }
}