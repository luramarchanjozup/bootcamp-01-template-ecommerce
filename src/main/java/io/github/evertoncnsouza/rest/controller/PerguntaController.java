package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Pergunta;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.UsuarioRepository;
import io.github.evertoncnsouza.rest.dto.Emails;
import io.github.evertoncnsouza.rest.dto.PerguntaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//5 PCI's
@RestController
@RequestMapping("produtos/{id}/pergunta")
public class PerguntaController {

        @PersistenceContext
        private EntityManager manager;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private Emails emails;


        @PostMapping
        @Transactional
        public String save(@RequestBody @Valid PerguntaRequest request,
                           @PathVariable("id") Long id){
            Produto produto = manager.find(Produto.class, id);
            Usuario navegador = usuarioRepository.findByEmail("everton@gmail.com").get();
            Pergunta pergunta = request.toModel(produto, navegador);
            manager.persist(pergunta);
            emails.pergunta(pergunta);
            return pergunta.toString();


        }

    }
