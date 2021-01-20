package br.com.zup.ecomerce.nicolle.request;

import javax.validation.constraints.NotNull;

import br.com.zup.ecomerce.nicolle.model.Categoria;
import br.com.zup.ecomerce.nicolle.repository.CategoriasRepository;
import br.com.zup.ecomerce.nicolle.validator.SemValorRepetido;

public class CategoriaRequest {
	
	@NotNull
	@SemValorRepetido(dominioClasse = Categoria.class, nomeCampo = "nome")
	private String nome;
	
	private Long idCategoriaMae;

	

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public Categoria novaCategoria(CategoriasRepository repository) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = repository.findById(idCategoriaMae).get();
			categoria.setMae(categoriaMae);
		}		
		return categoria;
	}
	
	
	

}
