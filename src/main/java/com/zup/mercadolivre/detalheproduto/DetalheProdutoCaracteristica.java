package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.produto.caracteristica.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {

    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
