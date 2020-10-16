package br.com.ecommerce.adicionaresposta;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.seguranca.AutorizacaoDonoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos/{produtoId}/resposta")
public class RespostaController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AutorizacaoDonoProduto autorizacaoDonoProduto;

    @PostMapping
    @Transactional
    public ResponseEntity<?> responder(@RequestBody @Valid RespostaRequest respostaRequest,
                                       HttpServletRequest request, @PathVariable Long produtoId){

        Produto produto = entityManager.find(Produto.class, produtoId);

        if(autorizacaoDonoProduto.donoDoProduto(request, produto)){

            Resposta resposta = respostaRequest.converteParaTipoResposta(entityManager);

            entityManager.persist(resposta);

            return ResponseEntity
                    .ok()
                    .build();

        }

        return ResponseEntity.badRequest().build();

    }
}
