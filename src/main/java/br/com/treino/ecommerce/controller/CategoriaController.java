package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Categoria;
import br.com.treino.ecommerce.request.NovaCategoriaRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "/categorias")
    @Transactional
    public String novaCategoria(@RequestBody @Valid NovaCategoriaRequest request){ //1

        Categoria novaCategoria = request.toCategoria(entityManager); //2
        entityManager.persist(novaCategoria);
        return novaCategoria.toString();
    }

}
