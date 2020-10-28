package br.com.mercadolivre.dto.enums;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.mercadolivre.model.Compra;

//Contagem de Pontos - TOTAL:1
//1 - Compra

public enum GatewayPagamentoEnum {


	pagseguro {
		@Override
		public String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/v1/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		public String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/v1/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	public abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
