package br.com.mercadolivre.dto.retorno;

import br.com.mercadolivre.model.Compra;

//Contagem de Pontos - TOTAL:1
//1 - Compra

public class NotaFiscalRetornoDTO {

	private Long idCompra;
	private Long idComprador;
	
	@Deprecated
	public NotaFiscalRetornoDTO() {
	}
	
	public NotaFiscalRetornoDTO(Compra compra) {
		this.idCompra = compra.getId();
		this.idComprador = compra.getComprador().getId();
	}


	public Long getIdCompra() {
		return idCompra;
	}


	public Long getIdComprador() {
		return idComprador;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public void setIdComprador(Long idComprador) {
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "NoteFiscalRetornoDTO [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}
	
	
	
	
}
