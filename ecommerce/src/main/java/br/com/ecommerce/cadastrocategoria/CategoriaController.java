package br.com.ecommerce.cadastrocategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    /* pontos de dificuldade de entendimento = 2 */

    private final EntityManager entityManager;

    public CategoriaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCategoria(@RequestBody @Valid CadastroCategoriaRequest cadastroCategoriaRequest,
                                            UriComponentsBuilder uriComponentsBuilder){

        /* @complexidade (2) = método em classe específica  */
        var categoria = cadastroCategoriaRequest.converterParaTipoCategoria();
        entityManager.persist(categoria);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/categorias").buildAndExpand().toUri())
                .body(categoria);

    }
}
