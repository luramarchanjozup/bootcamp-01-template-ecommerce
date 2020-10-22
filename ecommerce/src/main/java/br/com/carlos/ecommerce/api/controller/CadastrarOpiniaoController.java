package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestOpiniaoDto;
import br.com.carlos.ecommerce.config.security.TokenManager;
import br.com.carlos.ecommerce.domain.entity.Opiniao;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastrarOpiniaoController {

    @PersistenceContext
    EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenManager tokenManager;

    @Transactional
    @PostMapping("/produtos/{id}/opinioes")
    public ResponseEntity<?> adicionar(@PathVariable("id") Long id, @Valid @RequestBody RequestOpiniaoDto request, HttpServletRequest servletRequest){
        Produto produto = manager.find(Produto.class, id);
        var usuarioLogado = tokenManager.getUserName(servletRequest.getHeader("Authorization"));
        var dono = usuarioRepository.findByLogin(usuarioLogado);
        Opiniao novaOpiniao = request.toEntity(produto, dono.get());
        manager.persist(novaOpiniao);

        return ResponseEntity.ok().build();

        }
}
