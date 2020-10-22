package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.service.Emails;
import io.github.evertoncnsouza.domain.service.NotaFiscal;
import io.github.evertoncnsouza.domain.service.Ranking;
import io.github.evertoncnsouza.rest.dto.RetornoPagSeguroRequest;
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
public class PagamentoComPagSeguroController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NotaFiscal notaFiscal;
    //PCI 1

    @Autowired
    private Ranking ranking;
    //PCI 2

    @Autowired
    private Emails email;
    //PCI 3

    @PostMapping(value = "retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra,
                                         @Valid RetornoPagSeguroRequest request,
                                         UriComponentsBuilder uriComponentsBuilder) {
       Compra compra = manager.find(Compra.class, idCompra);
            compra.adicionaTransacao(request);
            manager.merge(compra);
            //PCI 4, 5 e 6

            if (compra.processadaComSucesso()) {

             notaFiscal.processa(compra);
            ranking.processa(compra);
             email.novaVenda(compra);

             //PCI 7
            }
            else{
                email.vendaFalhou(compra, uriComponentsBuilder);

            }

            return compra.toString();
        }

}