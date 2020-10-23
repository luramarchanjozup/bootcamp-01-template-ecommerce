package com.zup.mercadolivre.fluxocompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CompraParte2Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EventosCompra eventosCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagseguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request,
                                         UriComponentsBuilder uriComponentsBuilder) {
        return processa(idCompra, request, uriComponentsBuilder);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {
        return processa(idCompra, request, uriComponentsBuilder);
    }

    private String processa(Long idCompra, @Valid RetornoGatewayPagamento retornoGatewayPagamento,
                            UriComponentsBuilder uriComponentsBuilder) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventosCompra.processa(compra, uriComponentsBuilder);
        return compra.toString();
    }
}
