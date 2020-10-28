package br.com.zup.ecommerce.entities.produto.pergunta;

import java.time.LocalDateTime;

/**
 * Contagem de carga intrínseca da classe: 1
 */

public class PerguntaProdutoRetorno {

    private String titulo;
    private LocalDateTime dataCadastro;

    //1
    public PerguntaProdutoRetorno(PerguntaProduto pergunta) {
        this.titulo = pergunta.getTitulo();
        this.dataCadastro = pergunta.getDataCadastro();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
