package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("retorno")
public class FechamentoCompraParte2Controller {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {

        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(request);

        manager.merge(compra);

        return compra.toString();
    }

    @PostMapping("paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {

        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(request);

        manager.merge(compra);

        return compra.toString();
    }
}