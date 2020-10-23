package br.com.treino.ecommerce.response;

import br.com.treino.ecommerce.model.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetalheProdutoResponse{

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<ImagemProduto> listaImagensProduto; //1
    private List<Caracteristica> listaCaracteristicasProduto; //2
    private double mediaNotas;
    private int numNotas;
    private List<Opiniao> listaOpinioesProduto; //3
    private List<Pergunta> listaPerguntasProduto; //4

    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.listaImagensProduto = produto.getImagemProduto();
        this.listaCaracteristicasProduto =
                produto.getCaracteristicas();
        this.listaOpinioesProduto = produto.getOpinioes();
        OpinioesResponse opinioesResponse =
                new OpinioesResponse(listaOpinioesProduto); //5
        this.mediaNotas = opinioesResponse.media();
        this.numNotas = opinioesResponse.total();
        this.listaPerguntasProduto = produto.getPerguntas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ImagemProduto> getListaImagensProduto() {
        return listaImagensProduto;
    }

    public List<Caracteristica> getListaCaracteristicasProduto() {
        return listaCaracteristicasProduto;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getNumNotas() {
        return numNotas;
    }

    public List<Opiniao> getListaOpinioesProduto() {
        return listaOpinioesProduto;
    }

    public List<Pergunta> getListaPerguntasProduto() {
        return listaPerguntasProduto;
    }

    @Override
    public String toString() {
        return "DetalheProdutoResponse{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", listaImagensProduto=" + listaImagensProduto +
                ", listaCaracteristicasProduto=" + listaCaracteristicasProduto +
                ", listaOpinioesProduto=" + listaOpinioesProduto +
                ", listaPerguntasProduto=" + listaPerguntasProduto +
                '}';
    }


}
