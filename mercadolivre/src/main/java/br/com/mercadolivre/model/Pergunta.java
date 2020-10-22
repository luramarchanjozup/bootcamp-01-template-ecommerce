package br.com.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Contagem de Pontos - TOTAL:2
//1 - Usuario
//1 - Produto

@Entity
public class Pergunta implements Comparable<Pergunta>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	@ManyToOne @NotNull
	Usuario usuario;
	@ManyToOne @NotNull
	Produto produto;
	private LocalDateTime instantecriacao = LocalDateTime.now();
	
	
	@Deprecated
	public Pergunta() {
	}
	
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}


	public String getTitulo() {
		return titulo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public Produto getProduto() {
		return produto;
	}


	public LocalDateTime getInstantecriacao() {
		return instantecriacao;
	}


	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + ", usuario=" + usuario + ", produto=" + produto + ", instantecriacao="
				+ instantecriacao + "]";
	}

	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo);
	}
	
	
	
}
