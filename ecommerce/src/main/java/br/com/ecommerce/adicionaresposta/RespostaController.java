package br.com.ecommerce.adicionaresposta;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.seguranca.AutorizacaoDonoProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos/{produtoId}/respostas")
public class RespostaController {


    private final EntityManager entityManager;

    private final AutorizacaoDonoProduto autorizacaoDonoProduto;


    public RespostaController(EntityManager entityManager, AutorizacaoDonoProduto autorizacaoDonoProduto) {
        this.entityManager = entityManager;
        this.autorizacaoDonoProduto = autorizacaoDonoProduto;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> responder(@RequestBody @Valid RespostaRequest respostaRequest,
                                       HttpServletRequest request, @PathVariable Long produtoId){

        var produto = entityManager.find(Produto.class, produtoId);
        if(!autorizacaoDonoProduto.donoDoProduto(request, produto)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var resposta = respostaRequest.converteParaTipoResposta(entityManager);
        entityManager.persist(resposta);

        return ResponseEntity
                .ok()
                .build();

    }
}
