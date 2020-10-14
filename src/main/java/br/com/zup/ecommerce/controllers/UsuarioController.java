package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.entities.usuario.UsuarioNovoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    //2
    public ResponseEntity<Usuario> cadastroUsuarios(@RequestBody @Valid UsuarioNovoRequest usuarioNovo) {

        Usuario usuario = usuarioNovo.toModel();
        manager.persist(usuario);
        return ResponseEntity.ok().body(usuario);
    }

}
