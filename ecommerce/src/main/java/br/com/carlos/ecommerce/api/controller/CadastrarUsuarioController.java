package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.RequestUsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastrarUsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping(value = "/usuarios")                       //1
    public ResponseEntity<?> adicionar(@Valid @RequestBody RequestUsuarioDto request) {
        //1
    var usuario = request.toModel();
    manager.persist(usuario);
    return ResponseEntity.ok().build();
    }
}
