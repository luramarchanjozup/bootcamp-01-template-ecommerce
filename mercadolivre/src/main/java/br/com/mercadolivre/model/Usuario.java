package br.com.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Contagem de Pontos - TOTAL:0


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Email
	private String loguin;
	@NotBlank @Length(min=6)
	private String senha;
	private LocalDateTime datacriacao = LocalDateTime.now();
	
	
	@Deprecated
	public Usuario() {
	}


	public Usuario(@NotBlank @Email String loguin, @NotBlank @Length(min = 6) String senha) {
		super();
		this.loguin = loguin;
		this.senha = senha;
	}

	public String getLoguin() {
		return loguin;
	}
	
	@JsonIgnore
	public Long getId() {
		return id;
	}


	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonIgnore
	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", loguin=" + loguin + ", senha=" + senha + ", datacriacao=" + datacriacao + "]";
	}
	
	
}
