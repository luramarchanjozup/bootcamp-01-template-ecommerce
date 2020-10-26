package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.CaracteristicaProduto;

public class ResponseCaracteristicaDto {

    private String nome;
    private String descricao;

    public ResponseCaracteristicaDto(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
