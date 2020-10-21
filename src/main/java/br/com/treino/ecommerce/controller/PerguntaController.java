package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Pergunta;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.request.NovaPerguntaRequest;
import br.com.treino.ecommerce.shared.UsuarioLogado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/produtos")
public class PerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(PerguntaController.class);

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity novaPergunta(@PathVariable("id") Long id,
                                       @RequestBody @Valid NovaPerguntaRequest request,
                                       @AuthenticationPrincipal UsuarioLogado interessado){ //1 //2

        Optional<Produto> possivelProduto = Optional
                .ofNullable(entityManager.find(Produto.class, id)); //3

        if (possivelProduto.isEmpty()) //4
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Pergunta novaPergunta = request
                .toPergunta(possivelProduto.get(), interessado); //5

        entityManager.persist(novaPergunta);

        return new ResponseEntity(novaPergunta, HttpStatus.CREATED);
    }

}
