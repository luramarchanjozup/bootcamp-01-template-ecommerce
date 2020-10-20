package io.github.evertoncnsouza.domain.service;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.repository.EventoCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        else {
        }
    }
}
