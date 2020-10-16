package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.categoria.Categoria;
import br.com.zup.ecommerce.entities.categoria.CategoriaNovoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    //2
    public ResponseEntity<Categoria> cadastroCategorias(@RequestBody @Valid CategoriaNovoRequest categoriaNova) {
        Categoria categoria = categoriaNova.toModel(manager);
        manager.persist(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);

    }

}
