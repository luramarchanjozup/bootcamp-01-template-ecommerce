package com.github.marcoscoutozup.ecommerce.caracteristica;

import javax.validation.constraints.NotBlank;

public class CaracteristicaDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String caracteristica;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

            //1
    public Caracteristica toModel(){
        return new Caracteristica(titulo,caracteristica);
    }

}
