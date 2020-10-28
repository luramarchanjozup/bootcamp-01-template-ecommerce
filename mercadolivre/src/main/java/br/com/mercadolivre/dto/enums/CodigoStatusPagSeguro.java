package br.com.mercadolivre.dto.enums;

//Contagem de Pontos - TOTAL:2
//1 - StatusTransacao
//1 - If

public enum CodigoStatusPagSeguro {

	SUCESSO,ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		
		return StatusTransacao.erro;
	}
}
