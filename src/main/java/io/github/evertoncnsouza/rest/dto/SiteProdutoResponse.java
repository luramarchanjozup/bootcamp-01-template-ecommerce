package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.CaracteristicaProduto;
import io.github.evertoncnsouza.domain.entity.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class SiteProdutoResponse {

    private Set<SiteCaracteristicaResponse> caracteristica;
    private SiteCategoriaResponse categoria;
    private SiteOpiniaoResponse opiniao;
    private String nome;
    private int quantidade;
    private String descricao;
    private BigDecimal valor;

    public SiteProdutoResponse(Produto produto) {
        nome = produto.getNome();
        categoria = new SiteCategoriaResponse(produto.getCategoria());
       caracteristica = produto.mapeiaCaracteristicas(SiteCaracteristicaResponse::new);
      //  opiniao = new SiteOpiniaoResponse(produto.getOpiniao());
        quantidade = produto.getQuantidade();
        descricao = produto.getDescricao();
        valor = produto.getValor();


    }

    public Set<SiteCaracteristicaResponse> getCaracteristica() {
        return caracteristica;
    }

    public SiteCategoriaResponse getCategoria() {
        return categoria;
    }

    public SiteOpiniaoResponse getOpiniao() {
        return opiniao;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
