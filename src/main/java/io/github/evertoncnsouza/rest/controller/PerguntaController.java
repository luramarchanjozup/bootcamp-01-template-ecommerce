package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Pergunta;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.Usuarios;
import io.github.evertoncnsouza.domain.service.Emails;
import io.github.evertoncnsouza.rest.dto.PerguntaRequest;
import io.github.evertoncnsouza.domain.service.impl.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        private Usuarios usuarios;

        @Autowired
        private Emails emails;

        @PostMapping
        @Transactional
        public String save(@RequestBody @Valid PerguntaRequest request,
                           @PathVariable("id") Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
            Produto produto = manager.find(Produto.class, id);
            Usuario navegador = usuarioLogado.get();
            Pergunta pergunta = request.toModel(produto, navegador);
            manager.persist(pergunta);
            emails.pergunta(pergunta);
            return pergunta.toString();
        }
    }
