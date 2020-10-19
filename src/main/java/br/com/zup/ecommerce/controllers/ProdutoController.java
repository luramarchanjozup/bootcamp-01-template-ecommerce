package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.ProdutoNovoRequest;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.Uploader;
import br.com.zup.ecommerce.validations.produto.CaracteristicasSemRepeticaoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private Uploader uploader;

    @InitBinder(value = "produtoNovoRequest")
    public void init(WebDataBinder binder) {
        //1
        binder.addValidators(new CaracteristicasSemRepeticaoValidacao());
    }

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<String> cadastroProdutos(@RequestBody @Valid ProdutoNovoRequest novoProduto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //1
        UsuarioLogado userDetails = (UsuarioLogado) authentication.getPrincipal();

        //1
        Produto produto = novoProduto.toModel(manager, userDetails.getUsuario());
        manager.persist(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto " +produto.getId()+" cadastrado");
    }

    @PostMapping("{id}/imagens")
    @Transactional
    //1
    public ResponseEntity<String> envioImagens(@PathVariable("id") Long id, @Valid ImagensNovoRequest novasImagens) {

        Set<String> links = uploader.enviaImagens(novasImagens.getImagens());

        Produto produto = manager.find(Produto.class, id);
        produto.incluirImagens(links);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioLogado userDetails = (UsuarioLogado) authentication.getPrincipal();

        //1
        if(!produto.isDono(userDetails.getUsuario().getId(), manager)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        manager.merge(produto);

        return ResponseEntity.ok(links.toString());
    }

}
