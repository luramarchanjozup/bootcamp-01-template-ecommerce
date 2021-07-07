package br.com.ecommerce.fazerpergunta;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long produtoId;


    public PerguntaRequest(String titulo, Long usuarioId, Long produtoId) {

        this.titulo = titulo;

        this.usuarioId = usuarioId;

        this.produtoId = produtoId;

    }

    public Pergunta converteParaTipoPergunta(EntityManager entityManager){

        Usuario usuario = entityManager
                .find(Usuario.class, usuarioId);

        Produto produto = entityManager
                .find(Produto.class, produtoId);

        return new Pergunta(titulo, usuario, produto);

    }
}
