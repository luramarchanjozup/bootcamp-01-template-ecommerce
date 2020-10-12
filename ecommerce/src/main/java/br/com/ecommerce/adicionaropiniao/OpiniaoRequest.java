package br.com.ecommerce.adicionaropiniao;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OpiniaoRequest {

    @NotNull
    @Size(min = 1, max = 5)
    private BigDecimal nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long produtoId;

    public OpiniaoRequest(@NotBlank BigDecimal nota, @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao, @NotNull Long usuarioId,
                          @NotNull Long produtoId) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.produtoId = produtoId;

    }

    public Opiniao converteParaTipoOpiniao(EntityManager entityManager){

        Usuario usuario = entityManager
                .find(Usuario.class, usuarioId);

        Produto produto = entityManager
                .find(Produto.class, produtoId);

        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
