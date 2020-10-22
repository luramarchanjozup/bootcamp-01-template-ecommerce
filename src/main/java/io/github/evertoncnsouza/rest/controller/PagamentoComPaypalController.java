package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.service.Emails;
import io.github.evertoncnsouza.domain.service.NotaFiscal;
import io.github.evertoncnsouza.domain.service.Ranking;
import io.github.evertoncnsouza.rest.dto.RetornoPaypalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//7 PCI's
@RestController
public class PagamentoComPaypalController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NotaFiscal notaFiscal;

    @Autowired
    private Ranking ranking;

    @Autowired
    private Emails email;

    @PostMapping(value = "retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra,
                                      @Valid RetornoPaypalRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(request);
        manager.merge(compra);

        if (compra.processadaComSucesso()) {

            notaFiscal.processa(compra);
            ranking.processa(compra);
            email.novaVenda(compra);
        }
        else{
            email.vendaFalhou(compra, uriComponentsBuilder);
        }
        return compra.toString();
    }

}

