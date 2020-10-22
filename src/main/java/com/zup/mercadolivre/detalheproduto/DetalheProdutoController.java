package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.produto.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {

    @PersistenceContext
    EntityManager manager;

    @GetMapping("/{id}")
    public DetalheProdutoView detalharProdutos(@PathVariable Long id) {
        Produto produtoEscolhido = manager.find(Produto.class, id);
        return new DetalheProdutoView(produtoEscolhido);
    }
}
