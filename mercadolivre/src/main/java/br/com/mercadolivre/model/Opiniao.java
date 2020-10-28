package br.com.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

//Contagem de Pontos - TOTAL:2
//1 - Usuario
//1 - Produto

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(value=1) @Max(value=5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank @Length(max = 500)
	private String descricao;
	@ManyToOne @NotNull
	Usuario usuario;
	@ManyToOne @NotNull
	Produto produto;
	
	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
	public String toString() {
		return "Opiniao [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario=" + usuario
				+ ", produto=" + produto + "]";
	}
	
	
}
