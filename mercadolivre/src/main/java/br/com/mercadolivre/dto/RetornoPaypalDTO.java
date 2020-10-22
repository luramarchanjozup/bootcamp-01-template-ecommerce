package br.com.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.mercadolivre.dto.enums.StatusTransacao;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Transacao;
import br.com.mercadolivre.validator.ValorUnico;

//Contagem de Pontos - TOTAL:3
//1 - Transacao
//1 - StatusTransacao
//1 - If

public class RetornoPaypalDTO implements RetornoGatewayGenericoPagamento{

	@Min(0)@Max(1)
	private int status;
	@NotBlank @ValorUnico(classe = Transacao.class, campo = "idTransacaoGateway")
	private String idTransacao;
	
	
	public RetornoPaypalDTO(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
		this.status = status;
		this.idTransacao = idTransacao;
	}


	public int getStatus() {
		return status;
	}


	public String getIdTransacao() {
		return idTransacao;
	}


	@Override
	public String toString() {
		return "RetornoPaypalDTO [status=" + status + ", idTransacao=" + idTransacao + "]";
	}


	@Override
	public Transacao toTransacao(Compra compra) {
		StatusTransacao status = StatusTransacao.sucesso;
		if (this.status == 0) {
			status = StatusTransacao.erro;
		}
		return new Transacao(status, idTransacao, compra);
	}
	
	
}
