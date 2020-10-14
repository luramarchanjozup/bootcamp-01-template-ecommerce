package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Categoria;
import io.github.evertoncnsouza.rest.dto.CategoriaRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//2 PCI's
@RestController
@RequestMapping("categorias")
public class CategoriaController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    //@ResponseStatus(HttpStatus.CREATED) Comentado para retornar 200;
    public String save (@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);
        return categoria.toString();
    }


}
