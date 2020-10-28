package br.com.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.validator.ValorUnico;

//Contagem de Pontos - TOTAL:1
//1 - Usuario

public class UsuarioDTO {
	
	@NotBlank @Email @ValorUnico(classe = Usuario.class, campo = "loguin")
	private String loguin;
	@NotBlank @Length(min=6)
	private String senha;
	
	
	public UsuarioDTO(@NotBlank @Email String loguin, @NotBlank @Length(min = 6) String senha){
		super();
		this.loguin = loguin;
		this.senha = senha;
	}

	public Usuario toModel() {
		String senhaCriptografada = hashSenha(this.senha);
		return new Usuario(this.loguin, senhaCriptografada);
	}
	
	public static String hashSenha (String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}

	public String getLoguin() {
		return loguin;
	}


	public String getSenha() {
		return senha;
	}


	@Override
	public String toString() {
		return "UsuarioDTO [loguin=" + loguin + ", senha=" + senha + "]";
	}
	
	
	
	

}
