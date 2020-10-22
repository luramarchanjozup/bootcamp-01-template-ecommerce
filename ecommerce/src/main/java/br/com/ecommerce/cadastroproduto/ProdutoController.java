package br.com.ecommerce.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProduto(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest){

        Produto produtoCadastrado = cadastroProdutoRequest.converteParaTipoProduto(entityManager);

        entityManager.persist(produtoCadastrado);

        return ResponseEntity
                .ok()
                .build();

    }
}
