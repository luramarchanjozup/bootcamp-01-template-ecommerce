package br.com.ecommerce.finalizacompra;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventosNovaCompra {

    private List<EventoCompraSucesso> eventosCompraSucesso;

    public EventosNovaCompra(List<EventoCompraSucesso> eventosCompraSucesso) {

        this.eventosCompraSucesso = eventosCompraSucesso;

    }

    public void processa(Compra compra) {

        if(compra.processadaComSucesso()){

            eventosCompraSucesso.forEach(evento -> evento.processa(compra));

        }
    }

}
