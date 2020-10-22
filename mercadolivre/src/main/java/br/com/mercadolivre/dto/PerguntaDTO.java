package br.com.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.mercadolivre.model.Pergunta;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.validator.IdExistente;

//Contagem de Pontos - TOTAL:3
//1 - Pergunta
//1 - Produto
//1 - Usuario


public class PerguntaDTO {

	@NotBlank
	private String titulo;
	@NotNull @IdExistente (classe = "Produto", campo = "id")
	private Long idProduto;

	@Deprecated
	public PerguntaDTO() {
	}
	
	public PerguntaDTO(@NotBlank String titulo, @NotBlank @Positive Long idProduto) {
		super();
		this.titulo = titulo;
		this.idProduto = idProduto;
	}
	
	public Pergunta toModel(EntityManager manager, Usuario usuario) {
		Produto produto = manager.find(Produto.class, this.idProduto);
		return new Pergunta(this.titulo, usuario, produto);
	}

	public String getTitulo() {
		return titulo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	@Override
	public String toString() {
		return "PerguntaDTO [titulo=" + titulo + "]";
	}
	
	
}
