package br.com.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

//Contagem de Pontos - TOTAL:0

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank 
	private String nome;
	@ManyToOne
	private Categoria categoriaMae;
	
	
	@Deprecated
	public Categoria() {
		super();
	}

	public Categoria(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}
	
	
	
	
}
