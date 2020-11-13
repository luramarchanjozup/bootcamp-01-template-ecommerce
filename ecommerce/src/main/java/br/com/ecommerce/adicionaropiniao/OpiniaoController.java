package br.com.ecommerce.adicionaropiniao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/opinioes")
public class OpiniaoController {


    private final EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(Opiniao.class);


    public OpiniaoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest,
                                          UriComponentsBuilder uriComponentsBuilder){

        var opiniao = opiniaoRequest.converteParaTipoOpiniao(entityManager);
        entityManager.persist(opiniao);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/opinioes").buildAndExpand().toUri())
                .body(opiniao);

    }
}
