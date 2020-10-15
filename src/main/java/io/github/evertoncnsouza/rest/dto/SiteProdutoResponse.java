package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Produto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

//PCI 8
public class SiteProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private SiteCategoriaResponse categoria;
    private Set<SiteCaracteristicaResponse> caracteristica;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private int total;
    private Set<String> linksImagens;
    private Set<Map<String, String>> perguntas;

    public SiteProdutoResponse(Produto produto) {
        nome = produto.getNome();
        descricao = produto.getDescricao();
        valor = produto.getValor();
        categoria = new SiteCategoriaResponse(produto.getCategoria());
        caracteristica = produto.mapeiaCaracteristicas(SiteCaracteristicaResponse::new);
        linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        SiteOpiniaoResponse siteOpiniaoResponse = produto.getOpinioes();
        this.opinioes = siteOpiniaoResponse.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),"descricao",opiniao.getDescricao());
        });
        mediaNotas = siteOpiniaoResponse.media();
        this.total = siteOpiniaoResponse.total();
        SitePerguntaResponse sitePerguntaResponse = produto.getPerguntas();
        this.perguntas = sitePerguntaResponse.mapeiaPerguntas(pergunta -> {
            return Map.of("titulo", pergunta.getTitulo());
        });
    }

    public String getNome() {
        return nome;
    }

    public Set<SiteCaracteristicaResponse> getCaracteristica() {
        return caracteristica;
    }

    public SiteCategoriaResponse getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<Map<String, String>> getPerguntas() {
        return perguntas;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotal() {
        return total;
    }
}
