package br.com.zup.ecomerce.nicolle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import br.com.zup.ecomerce.nicolle.mensageria.Emails;
import br.com.zup.ecomerce.nicolle.model.Compra;

public class EmailCompraComSucesso implements EventoCompraSucesso{
	
	@Autowired
	private Emails emails;

	public void processaCompra(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(), "Essa compra não foi concluida com sucesso!");
		emails.compraMensagemComNF(compra);
		
	}


}
