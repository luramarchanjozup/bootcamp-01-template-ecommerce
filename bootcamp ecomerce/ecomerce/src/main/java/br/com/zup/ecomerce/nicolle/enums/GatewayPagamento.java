package br.com.zup.ecomerce.nicolle.enums;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ecomerce.nicolle.model.Compra;

public enum GatewayPagamento {
	
	pagseguro{
		@Override
		public String criaUrlRetorno(Compra compra, UriComponentsBuilder builder) {
		
		URI urlPagseguro = UriComponentsBuilder.fromPath("/ecomerce/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toUri();
		
		return "pagseguro.com?returnId=" + compra.getId() + "&redirectUrl="+ urlPagseguro;
		
		}
		
	}, 
	paypal{
		@Override
		public String criaUrlRetorno(Compra compra, UriComponentsBuilder builder) {
		
		URI urlPaypal = UriComponentsBuilder.fromPath("/ecomerce/retorno-paypal/{id}").buildAndExpand(compra.getId()).toUri();
		
		return "paypal.com/" + compra.getId() + "?redirectUrl="+ urlPaypal;
		}
		
	};
	
	public abstract String criaUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);

}

