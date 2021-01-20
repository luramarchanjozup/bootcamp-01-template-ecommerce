package br.com.zup.ecomerce.nicolle.response;

import java.time.LocalDateTime;

import br.com.zup.ecomerce.nicolle.model.Usuario;

public class UsuarioResponse {
	

	
	private Long id;
	private String login;

	private LocalDateTime createdAt;
	
	
	public UsuarioResponse(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.createdAt = usuario.getCreatedAt();
	}
	
	
	public Long getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	

}
