package br.com.zup.ecomerce.nicolle.response;

import java.util.Optional;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zup.ecomerce.nicolle.model.Categoria;

public class CategoriaResponse {
	
	private Long id;
	private String nome;
	private Categoria categoriaMae;
	
	public CategoriaResponse(Categoria categoria) {

		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.categoriaMae = categoria.getCategoriaMae();
		if (categoriaMae == null) {
			System.out.println("chegou aqui no if do response");
			String categoriaMae = "Não existe categorias correlacionadas";
		}
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
	
	
	
	
	
	


}
