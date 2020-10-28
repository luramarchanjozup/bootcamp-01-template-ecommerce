package br.com.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//Contagem de Pontos - TOTAL:0

@Entity
public class Caracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	@Deprecated
	public Caracteristica () {
	}
	
	public Caracteristica(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}


	public String getDescricao() {
		return descricao;
	}
	

	@Override
	public String toString() {
		return "Caracteristica [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	
	
	
	
	
	
	
	
}
