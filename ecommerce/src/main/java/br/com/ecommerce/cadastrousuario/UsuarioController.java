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

    /* total de pontos de dificuldade de entendimento = 2 */

    private final EntityManager entityManager;

    public UsuarioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest,
                                          UriComponentsBuilder uriComponentsBuilder){

        /* @complexidade (2) = método em classe específica  */
        var usuario = cadastroUsuarioRequest.converterParaTipoUsuario();
        entityManager.persist(usuario);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/usuarios").buildAndExpand().toUri())
                .body(usuario);

    }
}
