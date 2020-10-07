package com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao;

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
@RequestMapping("/produto/adicionaropiniao")
public class AdicionarOpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired //1
    private JwtUtils jwtUtils;

    @PutMapping("/{idProduto}")
    @Transactional                                                      //2
    public ResponseEntity adicionarOpiniaoAoProduto(@RequestBody @Valid OpiniaoDTO dto, @PathVariable UUID idProduto, HttpServletRequest request){
        String email = jwtUtils.getEmail(request);
        Produto produto = entityManager.find(Produto.class, idProduto);

        Opiniao opiniao = dto.toModel(entityManager, email);

        //3
        if(produto == null){
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        produto.adicionarOpiniaoAoProduto(opiniao);

        entityManager.merge(produto);
        return ResponseEntity.ok(produto.toString());
    }
}
