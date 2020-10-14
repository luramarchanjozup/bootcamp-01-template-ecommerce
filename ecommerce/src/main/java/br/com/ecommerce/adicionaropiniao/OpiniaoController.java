package br.com.ecommerce.adicionaropiniao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest){

        Opiniao opiniao = opiniaoRequest
                .converteParaTipoOpiniao(entityManager);

        entityManager.persist(opiniao);

        return ResponseEntity.ok().build();

    }
}
