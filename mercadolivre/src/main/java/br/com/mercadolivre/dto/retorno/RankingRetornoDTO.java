package br.com.mercadolivre.dto.retorno;

import br.com.mercadolivre.model.Compra;

//Contagem de Pontos - TOTAL:1
//1 - Compra

public class RankingRetornoDTO {

	private Long idCompra;
	private Long IdVendedor;
	
	
	@Deprecated
	public RankingRetornoDTO() {
	}
	
	public RankingRetornoDTO(Compra compra) {
		this.idCompra = compra.getId();
		this.IdVendedor = compra.getProdutoEscolhido().getUsuario().getId();
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdVendedor() {
		return IdVendedor;
	}
	
	

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public void setIdVendedor(Long idVendedor) {
		IdVendedor = idVendedor;
	}

	@Override
	public String toString() {
		return "RankingRetornoDTO [idCompra=" + idCompra + ", IdVendedor=" + IdVendedor + "]";
	}
	
	
	
}
