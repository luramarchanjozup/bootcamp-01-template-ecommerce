package com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Embeddable
public class Opiniao {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(columnDefinition = "text")
    private String descricao;

    @NotNull
    @ManyToOne //1
    private Usuario usuario;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@NotNull @Size(max = 5, min = 1) Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
