package br.com.zup.ecommerce.entities.produto.pergunta;

import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.validation.constraints.NotBlank;

/**
 * Contagem de carga intrínseca da classe: 2
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

    //2
    public PerguntaProduto toModelSemProduto(Usuario usuario){
        return new PerguntaProduto(titulo, usuario);
    }
}
