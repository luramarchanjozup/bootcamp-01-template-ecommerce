package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.mercadolivre.dto.RetornoGatewayGenericoPagamento;
import br.com.mercadolivre.model.Compra;

//Contagem de Pontos - TOTAL:4
//1 - Compra
//1 - RetornoGatewayGenericoPagamento
//1 - If
//1 - Else

@Service
public class RetornoPagamentoServices {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EventosCompraServices eventoscompraservices;
	
	public Compra processarPagamento(Long id, RetornoGatewayGenericoPagamento  retornogatewaygenericopagamento) {
		Compra compra = manager.find(Compra.class, id);
		
		Assert.notNull(compra,"Compra não encontrada para o ID informado");

		compra.adicionaTransacao(retornogatewaygenericopagamento);
		manager.merge(compra);
		
		if (compra.processadaComSucesso()) {
			eventoscompraservices.emiteNF(compra);
			eventoscompraservices.emiteRanking(compra);
			eventoscompraservices.enviaEmailSucesso(compra);
		}else {
			eventoscompraservices.enviaEmailFalhaDesenv(compra);
			//eventoscompraservices.enviaEmailFalhaProducao(compra);
		}
		
		return compra;
	}
	
}
