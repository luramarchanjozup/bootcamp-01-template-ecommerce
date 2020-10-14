package br.com.ecommerce.cadastrousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest){

        Usuario usuario = cadastroUsuarioRequest.converterParaTipoUsuario();

        entityManager.persist(usuario);

        return ResponseEntity
                .ok()
                .build();

    }
}
