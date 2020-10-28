package br.com.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.mercadolivre.model.Categoria;
import br.com.mercadolivre.validator.IdExistente;
import br.com.mercadolivre.validator.ValorUnico;

//Contagem de Pontos - TOTAL:1
//1 - Categoria

public class CategoriaDTO {

	@NotBlank @ValorUnico(classe = Categoria.class, campo = "nome")
	private String nome;
	@IdExistente(classe = "Categoria", campo = "id")
	private Long idMae;
	
	public CategoriaDTO(@NotBlank String nome, Long idMae) {
		super();
		this.nome = nome;
		this.idMae = idMae;
	}
	
	
	
	public CategoriaDTO(@NotBlank String nome) {
		super();
		this.nome = nome;
	}



	public Categoria toModel(EntityManager manager) {
		if (this.idMae != null) {
			Categoria mae = manager.find(Categoria.class, this.idMae);
			return new Categoria(this.nome, mae);
		}
		
		return new Categoria(this.nome);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdMae(Long idMae) {
		this.idMae = idMae;
	}
	@Override
	public String toString() {
		return "CategoriaDTO [nome=" + nome + ", idMae=" + idMae + "]";
	}
	
	
	
}
