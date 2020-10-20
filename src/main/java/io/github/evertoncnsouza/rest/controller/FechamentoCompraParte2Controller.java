package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.repository.RetornoGatewayPagamento;
import io.github.evertoncnsouza.domain.service.EventosNovaCompra;
import io.github.evertoncnsouza.rest.dto.RetornoPagSeguroRequest;
import io.github.evertoncnsouza.rest.dto.RetornoPaypalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
//@RequestMapping("retorno")
public class FechamentoCompraParte2Controller {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;


    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping(value = "/retorno/paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);

        eventosNovaCompra.processa(compra);

            return compra.toString();
    }
}