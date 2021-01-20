package br.com.zup.ecomerce.nicolle.response;

import br.com.zup.ecomerce.nicolle.model.Pergunta;
import br.com.zup.ecomerce.nicolle.model.Usuario;

public class PerguntaResponse {
	
	private Long id;
	private String titulo;
	private UsuarioResponse usuario;
	
	
	public PerguntaResponse(Pergunta pergunta) {
		this.id = pergunta.getId();
		this.titulo = pergunta.getTitulo();
		this.usuario = pergunta.getUsuario();
	}


	public Long getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}


	public UsuarioResponse getUsuario() {
		return usuario;
	}
	
	
	
	
	

}
