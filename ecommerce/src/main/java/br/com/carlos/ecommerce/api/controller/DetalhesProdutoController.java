package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.ResponseDetalhesProdutoDto;
import br.com.carlos.ecommerce.domain.entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalhesProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> listarDetalhesDoProduto(@PathVariable Long id){
        //1
        Produto produto = entityManager.find(Produto.class, id);
        //1
        if(produto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não encontrado");
        }                               //1
        return ResponseEntity.ok(new ResponseDetalhesProdutoDto(produto));
    }
}
