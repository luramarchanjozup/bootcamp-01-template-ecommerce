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
@RequestMapping("/detalhe")
public class DetalheProdutoController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/{produtoId}")                                                                                            
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable Long produtoId){

        //1
        Produto produto = entityManager.find(Produto.class, produtoId);

        //1
        DetalheProdutoResponse DetalhamentoDoProduto = new DetalheProdutoResponse(produto);

        return ResponseEntity
                .ok(DetalhamentoDoProduto);

    }
}
