package br.com.zup.ecommerce.entities.produto.pergunta;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.validation.constraints.NotBlank;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class PerguntaProdutoNovoRequest {
    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    //3
    public PerguntaProduto toModel(Produto produto, Usuario usuarioLogado) {
        return new PerguntaProduto(titulo, usuarioLogado, produto);
    }
}
