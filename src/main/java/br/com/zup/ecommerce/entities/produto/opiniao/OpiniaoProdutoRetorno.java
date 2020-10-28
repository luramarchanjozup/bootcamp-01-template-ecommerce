package br.com.zup.ecommerce.entities.produto.opiniao;


/**
 * Contagem de carga intrínseca da classe: 1
 */

public class OpiniaoProdutoRetorno {

    private int nota;
    private String titulo;
    private String descricao;

    //1
    public OpiniaoProdutoRetorno(OpiniaoProduto opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


}
