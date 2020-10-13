package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Pergunta;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.domain.repository.Users;
import io.github.evertoncnsouza.rest.dto.PerguntaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos/{id}/pergunta")
public class PerguntaController {


        @PersistenceContext
        private EntityManager manager;

        @Autowired
        private Users users;


        @PostMapping
        @Transactional
        public String save(@RequestBody @Valid PerguntaRequest request,
                           @PathVariable("id") Long id){
            Produto produto = manager.find(Produto.class, id);
            User navegador = users.findByEmail("everton@gmail.com").get();
            Pergunta pergunta = request.toModel(produto, navegador);
            manager.persist(pergunta);
/*
            RestTemplate restTemplate = new RestTemplate();
            MandrilMessage
            MandrilMail informacoesEmail = new MandrilMail(message);
            restTemplate.postForEntity("https://mandrillapp.com/api/messages/send.json",
                    informacoesEmail, String.class);*/

            return pergunta.toString();


        }

    }
