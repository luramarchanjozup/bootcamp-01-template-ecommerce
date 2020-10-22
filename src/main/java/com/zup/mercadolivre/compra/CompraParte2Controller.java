package com.zup.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CompraParte2Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private NotaFiscal notaFiscal;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagseguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, @Valid RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);

        if (compra.processadaComSucesso()) {
            notaFiscal.processa(compra);
            // falar com ranking
            // ranking.processa(compra);
            // mandar email para quem comprou
            // emailSucesso.processa(compra);
        }

        return compra.toString();
    }
}
