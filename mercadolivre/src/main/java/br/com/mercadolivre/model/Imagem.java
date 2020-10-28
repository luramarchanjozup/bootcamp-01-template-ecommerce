package br.com.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Contagem de Pontos - TOTAL:1
//1 - Produto



@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String link;
	@ManyToOne @NotNull
	private Produto produto;
	
	@Deprecated
	public Imagem() {
	}

	public Imagem(@NotBlank String link, @NotNull Produto produto) {
		super();
		this.link = link;
		this.produto = produto;
	}
	

	public String getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "Imagem [link=" + link + ", produto=" + produto + "]";
	}
	
	
}
