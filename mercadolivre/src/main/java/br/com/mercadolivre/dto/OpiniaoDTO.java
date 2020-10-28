package br.com.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import br.com.mercadolivre.model.Opiniao;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:3
//1 - Opiniao
//1 - Produto
//1 - Usuario

public class OpiniaoDTO {

	@Min(value=1) @Max(value=5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank @Length(max = 500)
	private String descricao;
	
	
	public OpiniaoDTO(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Opiniao toModel (Produto produto, Usuario usuario) {
		return new Opiniao(this.nota, this.titulo, this.descricao, usuario, produto);
	}

	@Override
	public String toString() {
		return "OpiniaoDTO [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}
	
	
	
	
}
