package com.github.marcoscoutozup.ecommerce.produto.adicionarpergunta;

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
@RequestMapping("/produto/adicionarpergunta")
public class AdicionarPerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired //1
    private JwtUtils jwtUtils;

    @PutMapping("/{idProduto}")
    @Transactional                                                      //2
    public ResponseEntity adicionarPerguntaAoProduto(@RequestBody @Valid PerguntaDTO dto, @PathVariable UUID idProduto, HttpServletRequest request){
        String email = jwtUtils.capturarEmailDoUsuarioLogadoNoToken(request);

        //3
        Produto produto = entityManager.find(Produto.class, idProduto);

        //4
        Pergunta pergunta = dto.toModel(entityManager, email);

        //5
        if(produto == null){
            return ResponseEntity.status(404).body("Produto não encontrado");
        }

        produto.adicionarPerguntaAoProduto(pergunta);

        System.out.println(produto.prepararPerguntaDoProdutoParaEmail());

        entityManager.merge(produto);
        return ResponseEntity.ok(PerguntaDTO.listaDePerguntasToDTO(produto.getPerguntas()));
    }

}
