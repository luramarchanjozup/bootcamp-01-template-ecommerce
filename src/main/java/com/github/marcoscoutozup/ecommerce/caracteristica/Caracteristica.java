package com.github.marcoscoutozup.ecommerce.caracteristica;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Caracteristica {

    @NotBlank
    private String titulo;

    @NotBlank
    private String caracteristica;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(@NotBlank String titulo, @NotBlank String caracteristica) {
        this.titulo = titulo;
        this.caracteristica = caracteristica;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "titulo='" + titulo + '\'' +
                ", caracteristica='" + caracteristica + '\'' +
                '}';
    }
}
