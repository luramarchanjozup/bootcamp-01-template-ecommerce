package br.com.ecommerce.cadastrousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final EntityManager entityManager;

    public UsuarioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest,
                                          UriComponentsBuilder uriComponentsBuilder){

        var usuario = cadastroUsuarioRequest.converterParaTipoUsuario();
        entityManager.persist(usuario);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/usuarios").buildAndExpand().toUri())
                .body(usuario);

    }
}
