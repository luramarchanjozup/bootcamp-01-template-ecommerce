package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.ImagemProduto;
import br.com.carlos.ecommerce.domain.entity.Pergunta;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.service.OpinioesService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ResponseDetalhesProdutoDto {
    private final String descricao;
    private final String nome;
    private final BigDecimal preco;
    //1
    private final Set<ResponseCaracteristicaDto> caracteristicas;
    private final Set<String> linksImagens;
    private final SortedSet<String> perguntas;
    private final Set<Map<String,String>> opinioes;
    private final double mediaNotas;
    private final int total;

    public ResponseDetalhesProdutoDto(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        //1
        this.caracteristicas = produto.mapeiaCaracteristicas(ResponseCaracteristicaDto::new);
        //1
        this.linksImagens = produto.mapeiaImagens(ImagemProduto::getLink);
        //1
        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);
        //1
        OpinioesService opinioes = produto.getOpinioes();
        //1
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao()));

        this.mediaNotas = opinioes.media();
        this.total = opinioes.total();

    }

    public int getTotal() {
        return total;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<ResponseCaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
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
}
