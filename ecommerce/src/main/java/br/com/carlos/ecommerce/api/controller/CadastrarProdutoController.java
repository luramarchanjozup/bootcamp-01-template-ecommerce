package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.RequestProdutoDto;
import br.com.carlos.ecommerce.api.handler.UnicidadeCaracteristicaValidator;
import br.com.carlos.ecommerce.config.security.TokenManager;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastrarProdutoController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired                  //1
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UnicidadeCaracteristicaValidator unicidade;
    @Autowired              //1
    private TokenManager tokenManager;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(unicidade);
    }

    @Transactional
    @PostMapping("produtos")                                //1
    public ResponseEntity<?> adicionar(@Valid @RequestBody RequestProdutoDto request, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest servletRequest){
        var usuarioLogado = tokenManager.getUserName(servletRequest.getHeader("Authorization"));
        var dono = usuarioRepository.findByLogin(usuarioLogado);

        //1
        var produto =  request.toModel(manager, dono.get());
        manager.persist(produto);
        return ResponseEntity.created(uriComponentsBuilder.path("/produtos/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }
}
