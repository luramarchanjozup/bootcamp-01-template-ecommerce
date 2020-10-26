package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestPagseguroDto;
import br.com.carlos.ecommerce.api.dto.RequestPaypalDto;
import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.service.impl.EventosNovaCompra;
import br.com.carlos.ecommerce.api.dto.RequestCheckoutPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FinalizarCompraController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired      //1
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional                                                                                   //1
    public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RequestPagseguroDto request) {
        return processa(idCompra, request);
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional                                                                             //1
    public ResponseEntity<?> processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RequestPaypalDto request) {
        return processa(idCompra, request);
    }
                                                            //1
    public ResponseEntity<?> processa(Long idCompra, RequestCheckoutPagamento request) {
            //1
        var compra = manager.find(Compra.class, idCompra);
        var compraComTransacao= request.toTransacao(compra);
        manager.merge(compraComTransacao);
        eventosNovaCompra.processa(compra);

        return ResponseEntity.ok().build();
    }
}
