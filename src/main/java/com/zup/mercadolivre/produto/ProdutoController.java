package com.zup.mercadolivre.produto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    public String cadastrarProduto(@Valid @RequestBody NovoProdutoRequest request) {
        Produto produto = request.toModel(manager);
        return request.toString();
    }
}
