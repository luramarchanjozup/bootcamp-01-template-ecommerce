package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Transacao;

//Contagem de Pontos - TOTAL:2
//1 - Transacao
//1 - Compra


public interface RetornoGatewayGenericoPagamento {

	Transacao toTransacao(Compra compra);
}
