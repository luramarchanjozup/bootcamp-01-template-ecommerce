package br.com.ecommerce.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produto")
    public ResponseEntity<?> criarProduto(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest){

        Produto produtoCadastrado = cadastroProdutoRequest.converteParaTipoProduto();

        produtoRepository.save(produtoCadastrado);

        return ResponseEntity.ok().build();

    }

}
