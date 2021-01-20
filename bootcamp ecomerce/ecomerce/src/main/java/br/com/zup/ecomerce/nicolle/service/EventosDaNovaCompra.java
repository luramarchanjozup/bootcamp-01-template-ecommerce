package br.com.zup.ecomerce.nicolle.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.ecomerce.nicolle.model.Compra;

@Service
public class EventosDaNovaCompra {
	
	@Autowired
	private Set<EventoCompraSucesso> eventoCompraSucesso;

	public void processaCompra(Compra compra) {
		if(compra.processadaComSucesso()) {
				
			eventoCompraSucesso.forEach(evento -> evento.processaCompra(compra));
			
		} else {
			
			System.out.println("Erro no evento, nota, ranking, email, conferir se compra foi processada com sucesso!");
		}
		
	}

}
