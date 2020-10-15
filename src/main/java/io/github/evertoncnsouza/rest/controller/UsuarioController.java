package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.rest.dto.UsuarioRequest;
import io.github.evertoncnsouza.validation.constraintvalidation.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

//3 PCI's

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EmailValidator emailValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @PostMapping
    @Transactional
    public String cria(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toUsuario();
        manager.persist(usuario);
        return usuario.toString();
    }

}
