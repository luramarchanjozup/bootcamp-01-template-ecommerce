package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.validator.SemValorRepetido;

public class UsuarioRequest {
	
	@NotBlank(message = "{login.not.blank}")
	@NotNull(message = "{login.not.null}")
	@Email(message = "{email.not.valid}")
	@SemValorRepetido(dominioClasse = Usuario.class, nomeCampo = "login")
	private String login;
	
	@NotBlank(message = "{senha.not.blank}")
	@NotNull(message = "{senha.not.null}")
	@Size(min = 6, message = "{senha.size}")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario novoUsuario() {
		String encriptedPass = new BCryptPasswordEncoder().encode(senha);
		this.senha = encriptedPass;
		
		return new Usuario(login, senha);
	}


	
	

}
