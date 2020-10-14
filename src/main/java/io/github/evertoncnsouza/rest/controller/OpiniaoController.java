package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Opiniao;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.domain.repository.Users;
import io.github.evertoncnsouza.rest.dto.OpiniaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//5 PCI's
@RestController
@RequestMapping("produtos/{id}/opiniao")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Users users;

    @PostMapping
    @Transactional
    public String save(@RequestBody @Valid OpiniaoRequest request,
                       @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        User navegador = users.findByEmail("everton@gmail.com").get();
        Opiniao opiniao = request.toModel(produto, navegador);
        manager.persist(opiniao);
        return opiniao.toString();
    }
}
