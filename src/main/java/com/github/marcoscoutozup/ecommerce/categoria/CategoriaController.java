package com.github.marcoscoutozup.ecommerce.categoria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional                                          //1
    public String cadastrarCategoria(@RequestBody @Valid CategoriaDTO dto){

        //2
        Categoria categoria = dto.toModel(entityManager);
        entityManager.persist(categoria);
        return categoria.toString();
    }

}
