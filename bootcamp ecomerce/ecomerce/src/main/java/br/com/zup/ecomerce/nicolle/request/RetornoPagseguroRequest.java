package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.ecomerce.nicolle.enums.StatusRetornoPagueseguro;
import br.com.zup.ecomerce.nicolle.model.Compra;
import br.com.zup.ecomerce.nicolle.model.Transacao;
import br.com.zup.ecomerce.nicolle.service.RetornoGatewayPagamento;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento{
	
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagueseguro status;
	
	
	
	public RetornoPagseguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagueseguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}



	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
	

}
