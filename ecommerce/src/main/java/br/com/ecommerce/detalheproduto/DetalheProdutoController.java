package br.com.ecommerce.detalheproduto;

import br.com.ecommerce.cadastroproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api/detalhes")
public class DetalheProdutoController {


    private final EntityManager entityManager;


    public DetalheProdutoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @GetMapping("/{produtoId}")
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable Long produtoId){


        var produto = entityManager.find(Produto.class, produtoId);

        var DetalhamentoDoProduto = new DetalheProdutoResponse(produto);

        return ResponseEntity.ok(DetalhamentoDoProduto);

    }
}
