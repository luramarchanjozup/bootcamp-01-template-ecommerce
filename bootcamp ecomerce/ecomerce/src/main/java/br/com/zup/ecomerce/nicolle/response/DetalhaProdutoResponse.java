package br.com.zup.ecomerce.nicolle.response;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

import br.com.zup.ecomerce.nicolle.model.DetalheProdutoCaracteristica;
import br.com.zup.ecomerce.nicolle.model.Produto;

public class DetalhaProdutoResponse {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private Set<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double mediasNotas;

	public DetalhaProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
					});
		
		
		Set<Integer> notas = produto.mapOpinioes(opiniao -> opiniao.getNota());
		IntStream medias = notas.stream().mapToInt(nota -> nota);
		OptionalDouble average = medias.average();
		if(average.isPresent()) {
			this.mediasNotas = average.getAsDouble();
		}
		
		
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

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public Set<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediasNotas() {
		return mediasNotas;
	}

	
	

}
