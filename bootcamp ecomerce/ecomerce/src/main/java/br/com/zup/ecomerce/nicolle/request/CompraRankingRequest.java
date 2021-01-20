package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.NotNull;

public class CompraRankingRequest {
	
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idVendedor;
	public CompraRankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}
	@Override
	public String toString() {
		return "CompraRankingRequest [idCompra=" + idCompra + ", idVendedor=" + idVendedor + "]";
	}
	public Long getIdCompra() {
		return idCompra;
	}
	public Long getIdVendedor() {
		return idVendedor;
	}
	
	
}
