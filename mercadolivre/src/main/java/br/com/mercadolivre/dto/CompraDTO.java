package br.com.mercadolivre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.mercadolivre.dto.enums.GatewayPagamentoEnum;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:3
//1 - Compra
//1 - Produto
//1 - Usuario


public class CompraDTO {

	
	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamentoEnum gateway;
	
	public CompraDTO(@Positive int quantidade, @NotNull Long idProduto, @NotNull GatewayPagamentoEnum gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}
	
	public Compra toModel(Produto produto, Usuario usuario) {
		return new Compra(this.quantidade, produto, usuario, this.gateway);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamentoEnum getGateway() {
		return gateway;
	}

	@Override
	public String toString() {
		return "CompraDTO [quantidade=" + quantidade + ", idProduto=" + idProduto + ", gateway=" + gateway + "]";
	}
	
	

}
