package br.com.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mercadolivre.dto.enums.CodigoStatusPagSeguro;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Transacao;
import br.com.mercadolivre.validator.ValorUnico;

//Contagem de Pontos - TOTAL:1
//1 - Transacao


public class RetornoPagSeguroDTO implements RetornoGatewayGenericoPagamento{

	
	@NotBlank @ValorUnico(classe = Transacao.class, campo = "idTransacaoGateway")
	private String idTransacao;
	@NotNull
	private CodigoStatusPagSeguro status;
	
	public RetornoPagSeguroDTO(@NotBlank String idTransacao, @NotNull CodigoStatusPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}


	public CodigoStatusPagSeguro getStatus() {
		return status;
	}

	@Override
	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status.normaliza(),this.idTransacao,compra);
	}
	
	@Override
	public String toString() {
		return "RetornoPagSeguroDTO [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	
}
