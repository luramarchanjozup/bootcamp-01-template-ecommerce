package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ecomerce.nicolle.enums.GatewayPagamento;

public class CompraRequest {
	
	@Positive
	private int quantidade;
	
	@NotNull
	private Long idProduto;
	
	@NotNull
	private GatewayPagamento gateway;

	public CompraRequest(@Positive int quantidade, @NotNull Long idProduto, GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public void setGateway(GatewayPagamento gateway) {
		this.gateway = gateway;
	}
	
	
	

}
