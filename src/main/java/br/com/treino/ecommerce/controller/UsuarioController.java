package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.request.NovoUsuarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping(value = "/usuarios")
    @Transactional
    public ResponseEntity novoUsuario(@RequestBody @Valid NovoUsuarioRequest request){ //1
        Usuario novoUsuario = request.toModel(); //2
        entityManager.persist(novoUsuario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    

}
