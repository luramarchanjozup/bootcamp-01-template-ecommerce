package com.zup.mercadolivre.usuario;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String cadastrarUsuario(@Valid @RequestBody NovoUsuarioRequest request) {
        Usuario novoUsuario = request.toModel();
        manager.persist(novoUsuario);
        return "Usuario " + novoUsuario.getEmail() + " cadastrado";
    }
}
