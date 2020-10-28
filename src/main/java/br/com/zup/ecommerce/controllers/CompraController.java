package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.compra.Compra;
import br.com.zup.ecommerce.entities.compra.CompraNovoRequest;
import br.com.zup.ecommerce.entities.compra.CompraRetorno;
import br.com.zup.ecommerce.entities.compra.transacao.RetornoTransacaoPaypalRequest;
import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.email.EnviarEmail;
import br.com.zup.ecommerce.validations.compra.EstoqueDisponivelValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

/**
 * Contagem de carga intrínseca da classe: 12
 */

@RestController
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private EnviarEmail enviarEmail;

    @Autowired
    //1
    private EstoqueDisponivelValidador estoqueDisponivelValidador;

    @InitBinder(value = "compraNovoRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(estoqueDisponivelValidador);
    }

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<String> realizarCompra(@RequestBody @Valid CompraNovoRequest compraNova, @AuthenticationPrincipal UserDetails user) {

        //1
        Produto produto = manager.find(Produto.class, compraNova.getIdProduto());

        //1
        if (!produto.abateEstoque(compraNova.getQuantidade())){
           return ResponseEntity.badRequest().body("Sem estoque");
        }

        //1
        UsuarioLogado userDetails = (UsuarioLogado) user;

        //1
        Compra compra = compraNova.toModel(produto, userDetails.getUsuario());

        manager.persist(compra);

        enviarEmail.enviarEmail(produto.getDono().getLogin(),"Interesse na compra do produto", "Há um interessado na compra do produto");

        //Definir redirecionamento
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setLocation(URI.create(compra.getLinkPagamento()));

        return ResponseEntity.status(302).headers(httpHeaders).build();
    }

    @PostMapping("{id}/retorno-paypal")
    @Transactional
    //1
    public ResponseEntity<String> retornoPaypal(@PathVariable("id") Long id, @Valid RetornoTransacaoPaypalRequest retornoPagamentoPaypal) {
        Compra compra = manager.find(Compra.class, id);

        //1
        if(compra == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        boolean salvoTransacao = compra.addTransacao(retornoPagamentoPaypal);
        manager.merge(compra);

        //1
        if (!salvoTransacao) {
            return ResponseEntity.badRequest().body("Compra já paga");
        }
        //1
        if (retornoPagamentoPaypal.getStatusTransacao() == 0) {
            return ResponseEntity.ok().body("Erro na transação do PayPal");
        }

        //1
        Usuario comprador = compra.getComprador();
        Usuario vendedor = compra.getProduto().getDono();


        CompraRetorno compraRetorno = new CompraRetorno(compra);

        String menssagem = "Segue os dados do pagamento de sua compra:\n\n" + compraRetorno.toString();
        enviarEmail.enviarEmail(comprador.getLogin(), "Pagamento da compra", menssagem);

        return ResponseEntity.ok("Pago");
    }
}
