package br.com.ecommerce.detalheproduto;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastroproduto.Produto;
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

    private List<String> caracteristicas;


    public DetalheProdutoResponse(Produto produto){


        this.nome = produto.getNome();


        this.preco = produto.getValor();


        this.descricao = produto.getDescricao();


        this.linksImagens = produto.listarLinks(imagem -> imagem.getLinkImagem());


        this.opinioes = produto.listarOpinioes(opiniao -> opiniao.getTitulo());


        this.perguntas = produto.listarPerguntas(pergunta -> pergunta.getTitulo());


        this.caracteristicas = produto.listarCaracteristicas(caracteristica -> caracteristica.getNome());


        List<Opiniao> opinioes = produto.getOpinioes();


        this.totalDeNotas = opinioes.size();


        this.mediaDeNotas = opinioes.stream()
                .map(opiniao -> opiniao.getNota())
                .mapToDouble(x -> x)
                .average();

    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public OptionalDouble getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public List<String> getOpinioes() {
        return opinioes;
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

}
