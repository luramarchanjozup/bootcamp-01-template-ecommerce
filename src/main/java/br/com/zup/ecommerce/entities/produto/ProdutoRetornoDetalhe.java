package br.com.zup.ecommerce.entities.produto;

import br.com.zup.ecommerce.entities.categoria.CategoriaRetorno;
import br.com.zup.ecommerce.entities.produto.caracteristica.CaracteristicasProdutoRetorno;
import br.com.zup.ecommerce.entities.produto.imagem.ImagemProduto;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProdutoRetorno;
import br.com.zup.ecommerce.entities.produto.pergunta.PerguntaProdutoRetorno;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contagem de carga intrínseca da classe: 9
 */

public class ProdutoRetornoDetalhe {

    private String nome;
    private BigDecimal valor;
    private int qtdDisponivel;
    private String descricao;
    private CategoriaRetorno categoria;
    //1
    private Set<CaracteristicasProdutoRetorno> caracteristicas;
    private Set<String> imagens;
    private double mediaNotas;
    private int qntNotas;
    //1
    private List<OpiniaoProdutoRetorno> opinioes;
    //1
    private List<PerguntaProdutoRetorno> perguntas;

    //1
    public ProdutoRetornoDetalhe(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.qtdDisponivel = produto.getQtdDisponivel();
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaRetorno(produto.getCategoria());
        //1
        this.caracteristicas = produto.getCaracteristicasProduto()
                .stream()
                .map(CaracteristicasProdutoRetorno::new)
                .collect(Collectors.toSet());
        //1
        this.imagens = produto.getImagens()
                .stream()
                .map(ImagemProduto::getLink)
                .collect(Collectors.toSet());
        //1
        this.opinioes = produto.getOpinioes()
                .stream()
                .map(OpiniaoProdutoRetorno::new)
                .collect(Collectors.toList());
        //1
        this.perguntas = produto.getPerguntas()
                .stream()
                .map(PerguntaProdutoRetorno::new)
                .collect(Collectors.toList());

        this.qntNotas = this.opinioes.size();
        this.mediaNotas = this.calculaMedia();

    }

    public double calculaMedia(){
        //1
        double somaNotas = opinioes.stream().mapToInt(OpiniaoProdutoRetorno::getNota).sum();
        return somaNotas/this.qntNotas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaRetorno getCategoria() {
        return categoria;
    }

    public Set<CaracteristicasProdutoRetorno> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getQntNotas() {
        return qntNotas;
    }

    public List<OpiniaoProdutoRetorno> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaProdutoRetorno> getPerguntas() {
        return perguntas;
    }
}
