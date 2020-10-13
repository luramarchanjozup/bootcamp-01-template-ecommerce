package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.CaracteristicaProduto;

public class SiteCaracteristicaResponse {

    private String nome;
    private String descricao;

    public SiteCaracteristicaResponse(CaracteristicaProduto caracteristica) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
