package com.github.marcoscoutozup.ecommerce.compra;

import com.github.marcoscoutozup.ecommerce.produto.Produto;
import com.github.marcoscoutozup.ecommerce.seguranca.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/produto/{idProduto}")
    @Transactional                                                                          //1
    public ResponseEntity efetuarCompra(@PathVariable UUID idProduto, @RequestBody @Valid CompraDTO dto, HttpServletRequest request){
        String email = jwtUtils.getEmail(request);

        //2
        Produto produto = entityManager.find(Produto.class, idProduto);

        //3
        if(produto == null){
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        //4
        if(!produto.verificarSeExisteEstoqueParaOperacao(dto.getQuantidade())){
            return ResponseEntity.status(400).body("Não existe estoque para a operação de compra");
        }

        produto.abaterEstoque(dto.getQuantidade());

        //5
        Compra compra = dto.toModel(entityManager, email, produto);

        entityManager.merge(produto);
        entityManager.persist(compra);

        System.out.println(compra.prepararDetalhesDaCompraParaEmail());

        return ResponseEntity.status(302).body(compra.retornarUrlDePagamentoDaCompra());
    }

}
