package br.com.ecommerce.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    /* total de pontos de dificuldade de entendimento = 2 */

    private final EntityManager entityManager;

    public ProdutoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProduto(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest,
                                          UriComponentsBuilder uriComponentsBuilder){

        /* @complexidade (2) = método em classe específica  */
        var produtoCadastrado = cadastroProdutoRequest.converteParaTipoProduto(entityManager);
        entityManager.persist(produtoCadastrado);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/produtos").buildAndExpand().toUri())
                .body(produtoCadastrado);

    }
}
