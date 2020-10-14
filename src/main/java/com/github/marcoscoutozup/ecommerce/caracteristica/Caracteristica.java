package com.github.marcoscoutozup.ecommerce.caracteristica;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getTitulo() {
        return titulo;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "titulo='" + titulo + '\'' +
                ", caracteristica='" + caracteristica + '\'' +
                '}';
    }
}
