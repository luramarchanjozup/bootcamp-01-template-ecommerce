package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.RequestProdutoDto;
import br.com.carlos.ecommerce.api.handler.UnicidadeCaracteristicaValidator;
import br.com.carlos.ecommerce.domain.entity.Usuario;
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
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastrarProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnicidadeCaracteristicaValidator unicidade;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(unicidade);
    }

    @Transactional
    @PostMapping("produtos")
    public ResponseEntity adicionar(@Valid @RequestBody RequestProdutoDto request, UriComponentsBuilder uriComponentsBuilder){
        var comprador = usuarioRepository.findByLogin("carlos@junior.com");
        var produto =  request.toModel(manager, comprador.get());
        manager.persist(produto);
        return ResponseEntity.created(uriComponentsBuilder.path("/produtos/{id}").
                buildAndExpand(produto.getId()).toUri()).build();
    }
}
