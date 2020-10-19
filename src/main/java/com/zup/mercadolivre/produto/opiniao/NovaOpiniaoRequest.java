package com.zup.mercadolivre.produto.opiniao;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class NovaOpiniaoRequest {
    @Min(1)
    @Max(5)
    @NotNull
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota, titulo, descricao, produto, consumidor);
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
