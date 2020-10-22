package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.produto.opiniao.Opinioes;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoView {

    private String descricao;
    private String nome;
    private BigDecimal valor;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Collection<Map<String, String>> opinioes;
    private double mediaNotas;
    private int totalDeOpinioes;

    public DetalheProdutoView(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapOpinioes(opiniao -> Map.of("titulo", opiniao.getTitulo(),
                "descricao", opiniao.getDescricao()));
        this.mediaNotas = opinioes.media();
        this.totalDeOpinioes = opinioes.totalDeOpinioes();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Collection<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalDeOpinioes() {
        return totalDeOpinioes;
    }
}
