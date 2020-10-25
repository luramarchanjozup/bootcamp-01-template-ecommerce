package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.RequestPerguntaDto;
import br.com.carlos.ecommerce.config.security.TokenManager;
import br.com.carlos.ecommerce.domain.entity.Pergunta;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import br.com.carlos.ecommerce.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CadastrarPerguntaController {
    
    @PersistenceContext
    private EntityManager manager;
    @Autowired              //1
    private TokenManager tokenManager;
    @Autowired                  //1
    private UsuarioRepository usuarioRepository;
    @Autowired      //1
    EmailService emailService;

    @Transactional
    @PostMapping(value = "/produtos/{id}/perguntas")                                        //1
    public ResponseEntity<?> adicionar(@PathVariable("id") Long id, @Valid @RequestBody RequestPerguntaDto request, HttpServletRequest servletRequest ) {
        //1
        Produto produto = manager.find(Produto.class, id);
        //1
        if(produto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não encontrado");
        }

        var usuarioLogado = tokenManager.getUserName(servletRequest.getHeader("Authorization"));
        var interessado = usuarioRepository.findByLogin(usuarioLogado);
        //1
        Pergunta novaPergunta = request.toEntity(produto, interessado.get());

        manager.persist(novaPergunta);
        emailService.enviarEmailNovaPergunta(novaPergunta);

        return ResponseEntity.ok(produto.mapeiaPerguntas(Pergunta::getTitulo));
    }
}
