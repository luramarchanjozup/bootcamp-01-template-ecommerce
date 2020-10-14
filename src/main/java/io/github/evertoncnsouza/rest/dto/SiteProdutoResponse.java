package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Produto;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

public class SiteProdutoResponse {

    private Set<SiteCaracteristicaResponse> caracteristica;
    private SiteCategoriaResponse categoria;
    private Set<String> opinioes;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private double mediaNotas;

    public SiteProdutoResponse(Produto produto) {
        nome = produto.getNome();
        categoria = new SiteCategoriaResponse(produto.getCategoria());
        caracteristica = produto.mapeiaCaracteristicas(SiteCaracteristicaResponse::new);
        perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        //Metodo Reference do Java;
        //Vantagem é que não expõe todas as características;
       opinioes = produto.mapeiaOpinioes(opiniao -> opiniao.getTitulo());
        linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        descricao = produto.getDescricao();
        valor = produto.getValor();


        Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
        IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble average = mapToInt.average();
        if(average.isPresent()) {
            this.mediaNotas = average.getAsDouble();
        }

            }


    public Set<SiteCaracteristicaResponse> getCaracteristica() {
        return caracteristica;
    }

    public SiteCategoriaResponse getCategoria() {
        return categoria;
    }


    public Set<String> getOpinioes() {
        return opinioes;
    }


    public String getNome() {
        return nome;
    }

     public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<String> getLinksImagens(){
        return linksImagens;
    }


    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }
}
