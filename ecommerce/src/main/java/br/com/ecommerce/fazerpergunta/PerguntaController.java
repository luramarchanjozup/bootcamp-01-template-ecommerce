package br.com.ecommerce.fazerpergunta;

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
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest){

        Pergunta pergunta = perguntaRequest.converteParaTipoPergunta(entityManager);

        entityManager.persist(pergunta);

        return ResponseEntity
                .ok()
                .build();

    }
}
