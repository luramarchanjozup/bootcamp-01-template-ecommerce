package br.com.ecommerce.detalheproduto;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastroproduto.ImagemProduto;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.fazerpergunta.Pergunta;
import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;


public class DetalheProdutoResponse {


    private String nome;

    private BigDecimal preco;

    private String descricao;

    private OptionalDouble mediaDeNotas;

    private Integer totalDeNotas;

    private List<String> opinioes;

    private List<String> linksImagens;

    private List<String> perguntas;

    private List<DetalheProdutoCaracteristica> caracteristicas;



    public DetalheProdutoResponse(Produto produto){

        this.linksImagens = produto.listarLinks(ImagemProduto::getLinkImagem);

        this.nome = produto.getNome();

        this.preco = produto.getValor();

        this.descricao = produto.getDescricao();

        this.opinioes = produto.listarOpinioes(Opiniao::getTitulo);

        this.perguntas = produto.listarPerguntas(Pergunta::getTitulo);

        this.caracteristicas = produto.listarCaracteristicas(DetalheProdutoCaracteristica::new);

        this.totalDeNotas = produto.total();

        this.mediaDeNotas = produto.media();


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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OptionalDouble getMediaDeNotas() {
        return mediaDeNotas;
    }

    public void setMediaDeNotas(OptionalDouble mediaDeNotas) {
        this.mediaDeNotas = mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public void setTotalDeNotas(Integer totalDeNotas) {
        this.totalDeNotas = totalDeNotas;
    }

    public List<String> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(List<String> opinioes) {
        this.opinioes = opinioes;
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public void setLinksImagens(List<String> linksImagens) {
        this.linksImagens = linksImagens;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<String> perguntas) {
        this.perguntas = perguntas;
    }

    public List<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<DetalheProdutoCaracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
