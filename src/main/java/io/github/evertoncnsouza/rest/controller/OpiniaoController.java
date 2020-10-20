package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Opiniao;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.Usuarios;
import io.github.evertoncnsouza.rest.dto.OpiniaoRequest;
import io.github.evertoncnsouza.seguranca.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private Usuarios usuarios;

    @PostMapping
    @Transactional
    public String save(@RequestBody @Valid OpiniaoRequest request,
                       @PathVariable("id") Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = manager.find(Produto.class, id);
        Usuario navegador = usuarioLogado.get();
        Opiniao opiniao = request.toModel(produto, navegador);
        manager.persist(opiniao);
        return opiniao.toString();
    }
}
