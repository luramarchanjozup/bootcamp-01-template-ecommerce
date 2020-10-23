package br.com.treino.ecommerce.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Caracteristica {

    private @NotBlank String titulo;
    private @NotBlank String especificacao;

    @Deprecated
    public Caracteristica(){}

    public Caracteristica(@NotBlank String titulo, @NotBlank String especificacao) {
        this.titulo = titulo;
        this.especificacao = especificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "titulo='" + titulo + '\'' +
                ", especificacao='" + especificacao + '\'' +
                '}';
    }

}
