package br.com.mercadolivre.dto.retorno;

import br.com.mercadolivre.model.Caracteristica;

//Contagem de Pontos - TOTAL:1
//1 - Caracteristica

public class CaracteristicaRetornoDTO {

	private String nome;
	private String descricao;

	public CaracteristicaRetornoDTO(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
