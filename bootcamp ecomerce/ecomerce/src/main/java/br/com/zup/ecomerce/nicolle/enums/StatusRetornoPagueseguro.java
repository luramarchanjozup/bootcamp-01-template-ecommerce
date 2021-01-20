package br.com.zup.ecomerce.nicolle.enums;

public enum StatusRetornoPagueseguro {
	
	SUCESSO, 
	ERRO;

	public StatusTransacao normaliza() {
		
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		
		return StatusTransacao.erro;
	}

}
