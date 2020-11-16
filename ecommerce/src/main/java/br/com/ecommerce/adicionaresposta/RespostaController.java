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

    /* total pontos de dificuldade de entendimento = 5 */

    private final EntityManager entityManager;

    /* @complexidade (1) = acoplamento contextual */
    private final AutorizacaoDonoProduto autorizacaoDonoProduto;


    public RespostaController(EntityManager entityManager, AutorizacaoDonoProduto autorizacaoDonoProduto) {
        this.entityManager = entityManager;
        this.autorizacaoDonoProduto = autorizacaoDonoProduto;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> responder(@RequestBody @Valid RespostaRequest respostaRequest,
                                       HttpServletRequest request, @PathVariable Long produtoId){

        /* @complexidade (2) = método em classe específica + branch */
        var produto = entityManager.find(Produto.class, produtoId);
        if(!autorizacaoDonoProduto.donoDoProduto(request, produto)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        /* @complexidade (2) = método em classe específica  */
        var resposta = respostaRequest.converteParaTipoResposta(entityManager);
        entityManager.persist(resposta);

        return ResponseEntity
                .ok()
                .build();

    }
}
