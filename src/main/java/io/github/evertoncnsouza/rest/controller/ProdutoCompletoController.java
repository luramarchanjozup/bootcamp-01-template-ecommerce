package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.rest.dto.SiteProdutoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//4 PCI's
@RequestMapping("completo")
@RestController
public class ProdutoCompletoController {
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("{id}")
    public ResponseEntity<?> completo(@PathVariable("id") Long id) {
        Produto produtoBuscado = manager.find(Produto.class, id);
        if (produtoBuscado == null) {
            return ResponseEntity.notFound().build();
        }
        SiteProdutoResponse siteProdutoResponse = new SiteProdutoResponse(produtoBuscado);
        return ResponseEntity.ok (siteProdutoResponse);
    }

}
