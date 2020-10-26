package br.com.carlos.ecommerce.domain.service.impl;

import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.service.EmailService;
import br.com.carlos.ecommerce.domain.service.EventoCompraSucessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired      //1
    private Set<EventoCompraSucessoService> eventosCompraSucesso;

    @Autowired      //1
    EmailService emailService;

    public void processa(Compra compra) {
        //1
        if(compra.processadaComSucesso()) {     //1
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        else {
            emailService.enviarEmailFalhaProcessarCompra(compra);
        }
    }
}
