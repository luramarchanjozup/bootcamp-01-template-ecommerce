package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.CaracteristicaProduto;

public class SiteCaracteristicaResponse {

    private String nome;
    private String descricao;

    public SiteCaracteristicaResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
