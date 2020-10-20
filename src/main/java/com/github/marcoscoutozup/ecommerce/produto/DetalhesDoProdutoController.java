package com.github.marcoscoutozup.ecommerce.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class DetalhesDoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity exibirDetalhesDoProduto(@PathVariable UUID id){
        //1
        Produto produto = entityManager.find(Produto.class, id);

        //2
        if(produto == null){
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        //3
        DetalhesDoProdutoResponse detalhesDoProduto = new DetalhesDoProdutoResponse(produto);

        return ResponseEntity.ok(detalhesDoProduto);
    }
}
