package br.com.mercadolivre.dto.retorno;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

import br.com.mercadolivre.model.Produto;

//Contagem de Pontos - TOTAL:3
//1 - Produto
//1 - If
//1 - opiniao -> opiniao.getNota()

public class OpiniaoRetornoDTO {
	
	private double mediaNotas;
	private int total;
	private Set<Map<String,String>> listaOpinioes;
	
	
	public OpiniaoRetornoDTO(Produto produto) {
		this.listaOpinioes = produto.mapeiaOpinioes(opiniao -> {return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());});
		
		Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
		IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
		OptionalDouble media = mapToInt.average();
		
		if(media.isPresent()) {
			this.mediaNotas = media.getAsDouble();
		}
		
		this.total = this.listaOpinioes.size();
	}

	
	
	public double getMediaNotas() {
		return mediaNotas;
	}


	public int getTotal() {
		return total;
	}


	public Set<Map<String, String>> getListaOpinioes() {
		return listaOpinioes;
	}

	
	
	
}
