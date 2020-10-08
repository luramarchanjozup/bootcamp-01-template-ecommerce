package com.github.marcoscoutozup.ecommerce.produto.adicionarpergunta;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
public class Pergunta {

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    private LocalDateTime created_at;

    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotBlank String pergunta, @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.usuario = usuario;
        this.created_at = LocalDateTime.now();
    }

    public String prepararPerguntaParaEmail(){
        return "Título: " + titulo +
                "\n\nPergunta: " + pergunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo='" + titulo + '\'' +
                ", pergunta='" + pergunta + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
