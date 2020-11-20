package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Compra;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.request.NovaCompraRequest;
import br.com.treino.ecommerce.shared.UsuarioLogado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/compras")
public class CompraController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public String novaCompra(@RequestBody @Valid NovaCompraRequest request,
                             @AuthenticationPrincipal UsuarioLogado comprador){

        //verificar se o produto existe
        Optional<Produto> possivelProduto = Optional.ofNullable(entityManager
                .find(Produto.class, request.getIdProduto()));

        if (possivelProduto.isEmpty()){
            return "Não é possível fazer uma compra de um produto que não existe!";
        }

        Compra novaCompra = request.toCompra(possivelProduto.get(), comprador);

        //enviar email da compra?

        //enviar compra para o gateway de pagamento

        return novaCompra.toString();
    }

}
