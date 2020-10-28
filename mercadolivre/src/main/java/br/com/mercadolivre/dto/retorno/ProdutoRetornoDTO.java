package br.com.mercadolivre.dto.retorno;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import br.com.mercadolivre.model.Produto;

//Contagem de Pontos - TOTAL:5
//1 - Produto
//1 - produto.mapeiaCaracteristicas(CaracteristicaRetornoDTO::new);
//1 - produto.mapeiaImagens(imagem -> imagem.getLink());
//1 -  produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());	
//1 - OpiniaoRetornoDTO

public class ProdutoRetornoDTO {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<CaracteristicaRetornoDTO> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private OpiniaoRetornoDTO opiniaoRetorno;


	public ProdutoRetornoDTO(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaRetornoDTO::new);
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());		
		this.opiniaoRetorno = new OpiniaoRetornoDTO(produto);
	}


	public String getDescricao() {
		return descricao;
	}


	public String getNome() {
		return nome;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public Set<CaracteristicaRetornoDTO> getCaracteristicas() {
		return caracteristicas;
	}


	public Set<String> getLinksImagens() {
		return linksImagens;
	}


	public SortedSet<String> getPerguntas() {
		return perguntas;
	}


	public OpiniaoRetornoDTO getOpiniaoRetorno() {
		return opiniaoRetorno;
	}	
	
}
