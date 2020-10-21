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
    @PostMapping("usuarios")
    public ResponseEntity<?> adicionar(@Valid @RequestBody RequestUsuarioDto request) {
    var uauario = request.toModel();
    manager.persist(uauario);
    return ResponseEntity.ok().build();
    }
}
