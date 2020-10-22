package br.com.mercadolivre.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mercadolivre.model.Imagem;
import br.com.mercadolivre.model.Produto;


//Contagem de Pontos - TOTAL:3
//1 - Imagem
//1 - For
//1 - Produto

public class ImagemDTO {

	@Size(min = 1) @NotNull
	private Set<String> imagens;

	
	@Deprecated
	public ImagemDTO() {
		super();
	}

	public ImagemDTO(@Size(min = 1) @NotNull Set<String> imagens) {
		super();
		this.imagens = imagens;
	}

	
	public List<Imagem> toModel(Produto produto) {
		List<Imagem> listaimagems = new ArrayList<>();
		for(String imagemLink : this.imagens) {
			Imagem imagem = new Imagem(imagemLink, produto);
			listaimagems.add(imagem);
		}
		
		return listaimagems;
	}
	
	public Set<String> getImagens() {
		return imagens;
	}
	

	public void setImagens(Set<String> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "ImagemDTO [imagens=" + imagens + "]";
	}
	
	
}
