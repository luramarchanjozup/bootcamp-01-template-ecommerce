package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.NotNull;

public class CompraNotaFiscalRequest {
	
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;
	
	
	public CompraNotaFiscalRequest(Long idCompra, Long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}


	@Override
	public String toString() {
		return "CompraNotaFiscalRequest [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}


	public Long getIdCompra() {
		return idCompra;
	}


	public Long getIdComprador() {
		return idComprador;
	}
	
	

}
