package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.ProdutoNovoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<String> cadastroProdutos(@RequestBody @Valid ProdutoNovoRequest novoProduto) {
        //1
        Produto produto = novoProduto.toModel(manager);
        manager.persist(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado");
    }

}
