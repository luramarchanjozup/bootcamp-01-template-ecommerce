package com.zup.mercadolivre.produto.pergunta;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaRequest {
    @NotBlank
    private String titulo;

    public Pergunta toModel(@NotNull @Valid Usuario consumidor, @Valid @NotNull Produto perguntante) {
        return new Pergunta(titulo, consumidor, perguntante);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
