package com.github.marcoscoutozup.ecommerce.caracteristica;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String caracteristica;

    @Deprecated
    public CaracteristicaDTO() {
    }

                                //1
    public CaracteristicaDTO(Caracteristica caracteristica) {
        this.titulo = caracteristica.getTitulo();
        this.caracteristica = caracteristica.getTitulo();
    }

    public Caracteristica toModel(){
        return new Caracteristica(titulo,caracteristica);
    }

    public static List<CaracteristicaDTO> converterListaDeCaracteristicasParaDTO(List<Caracteristica> caracteristicas){
                                            //2
        return caracteristicas.stream().map(CaracteristicaDTO::new).collect(Collectors.toList());
    }

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


}
