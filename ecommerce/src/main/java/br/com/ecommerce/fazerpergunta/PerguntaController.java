package br.com.ecommerce.fazerpergunta;

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
@RequestMapping("/api/perguntas")
public class PerguntaController {


    private final EntityManager entityManager;


    public PerguntaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest, UriComponentsBuilder uriComponentsBuilder){

        var pergunta = perguntaRequest.converteParaTipoPergunta(entityManager);
        entityManager.persist(pergunta);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/perguntas").buildAndExpand().toUri())
                .body(pergunta);

    }
}
