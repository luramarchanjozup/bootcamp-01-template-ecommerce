package br.com.zup.ecommerce.entities.produto.opiniao;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.validation.constraints.*;

/**
 * Contagem de carga intrínseca da classe: 2
 */

public class OpiniaoProdutoNovoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500, message = "{opiniao.descricao}")
    private String descricao;

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    //2
    public OpiniaoProduto toModel(Produto produto, Usuario usuarioLogado) {
        return new OpiniaoProduto(nota, titulo, descricao, usuarioLogado, produto);
    }
}
