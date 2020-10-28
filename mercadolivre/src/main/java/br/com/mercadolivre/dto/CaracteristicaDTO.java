package br.com.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.mercadolivre.model.Caracteristica;

//Contagem de Pontos - TOTAL:1
//1 - Caracteristica



public class CaracteristicaDTO {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public CaracteristicaDTO(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica toModel() {
		return new Caracteristica(this.nome, this.descricao);
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicaDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	
	
}
