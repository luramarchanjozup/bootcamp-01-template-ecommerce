package com.github.marcoscoutozup.ecommerce.produto;

import com.github.marcoscoutozup.ecommerce.caracteristica.CaracteristicaDTO;
import com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao.OpiniaoDTO;
import com.github.marcoscoutozup.ecommerce.produto.adicionarpergunta.PerguntaDTO;

import java.math.BigDecimal;
import java.util.List;

public class DetalhesDoProdutoResponse {

    private List<String> imagens;
    private String nome;
    private BigDecimal preco;
    private List<CaracteristicaDTO> caracteristicas;
    private String descricao;
    private BigDecimal mediaDasNotas;
    private Integer totalDeNotas;
    private List<OpiniaoDTO> opinioes;
    private List<PerguntaDTO> perguntas;

    public DetalhesDoProdutoResponse (Produto produto) {
        this.imagens = produto.getImagens();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.caracteristicas = CaracteristicaDTO.converterListaDeCaracteristicasParaDTO(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.mediaDasNotas = produto.calcularMediaDeNotas();
        this.totalDeNotas = produto.getTotaldeNotas();
        this.opinioes = OpiniaoDTO.converterListaDeOpinioesParaDTO(produto.getOpinioes());
        this.perguntas = PerguntaDTO.converterListaDePerguntasParaDTO(produto.getPerguntas());
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getMediaDasNotas() {
        return mediaDasNotas;
    }

    public void setMediaDasNotas(BigDecimal mediaDasNotas) {
        this.mediaDasNotas = mediaDasNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public void setTotalDeNotas(Integer totalDeNotas) {
        this.totalDeNotas = totalDeNotas;
    }

    public List<OpiniaoDTO> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(List<OpiniaoDTO> opinioes) {
        this.opinioes = opinioes;
    }

    public List<PerguntaDTO> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaDTO> perguntas) {
        this.perguntas = perguntas;
    }
}
