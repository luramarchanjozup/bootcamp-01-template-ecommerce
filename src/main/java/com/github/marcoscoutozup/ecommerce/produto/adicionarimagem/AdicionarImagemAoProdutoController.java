package com.github.marcoscoutozup.ecommerce.produto.adicionarimagem;

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
@RequestMapping("/produto/adicionarimagens")
public class AdicionarImagemAoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired //1
    private JwtUtils jwtUtils;

    @PutMapping("/{idProduto}")
    @Transactional                                                      //2
    public ResponseEntity adicionarImagemAoProduto(@RequestBody @Valid ImagemDTO imagens, @PathVariable UUID idProduto, HttpServletRequest request){
        String email = jwtUtils.getEmail(request);

        //3
        Produto produto = entityManager.find(Produto.class, idProduto);

        //4
        if(produto == null){
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        //5
        if(!produto.verificarSeEOProprietarioDoProduto(email)){
            return ResponseEntity.status(403).body("O usuário não tem permissão para alterar esse produto");
        }

        produto.adicionarListaDeImagensNoProduto(imagens.getImagens());

        entityManager.merge(produto);
        return ResponseEntity.ok(produto.toString());
    }

}
