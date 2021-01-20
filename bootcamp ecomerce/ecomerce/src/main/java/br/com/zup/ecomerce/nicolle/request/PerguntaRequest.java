package br.com.zup.ecomerce.nicolle.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.ecomerce.nicolle.model.Pergunta;
import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta novaPergunta(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		
		return new Pergunta(titulo, usuario, produto);
	}
	
	
	
	

}
