package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.response.DetalheProdutoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ListaProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoResponse> procuraProduto(@PathVariable("id") Long id){ //1

        Optional<Produto> possivelProduto = Optional.ofNullable(entityManager
                .find(Produto.class, id)); //2

        if(possivelProduto.isEmpty()){ //3
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(new DetalheProdutoResponse(possivelProduto.get()),
                HttpStatus.OK);

    }

}
