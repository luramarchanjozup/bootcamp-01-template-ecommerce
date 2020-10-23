package com.zup.mercadolivre.fluxocompra;

import com.zup.mercadolivre.email.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Service
public class EventosCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;
    @Autowired
    private Emails email;

    public void processa(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        if (compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
            email.compraConcluida(compra);
        } else {
            email.compraFalhou(compra, uriComponentsBuilder);
        }
    }
}
