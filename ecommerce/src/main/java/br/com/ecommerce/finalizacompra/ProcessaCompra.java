package br.com.ecommerce.finalizacompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProcessaCompra {


    @Autowired
    private EventosNovaCompra eventosNovaCompra;


    public String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento,
                           EntityManager entityManager) {

        Compra compra = entityManager.find(Compra.class, idCompra);

        compra.adicionaTransacao(retornoGatewayPagamento);

        entityManager.merge(compra);

        eventosNovaCompra.processa(compra);

        return compra
                .toString();

    }

}
