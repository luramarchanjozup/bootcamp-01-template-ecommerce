package br.com.zup.ecomerce.nicolle.response;

import br.com.zup.ecomerce.nicolle.model.Produto;

public class ProdutosResponse {
	
	private Long id;
	private String nome;
	
	
	
	
	public ProdutosResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
	}
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	

}
