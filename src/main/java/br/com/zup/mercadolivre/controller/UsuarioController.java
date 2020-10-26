package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.dto.request.UsuarioRequestDTO;
import br.com.zup.mercadolivre.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity createUser (@RequestBody @Valid UsuarioRequestDTO request) {
        Usuario user = request.toModel();
        entityManager.persist(user);
        return ResponseEntity.ok().build();
    }
}
