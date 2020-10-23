package com.zup.mercadolivre.produto.caracteristica;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.produto.caracteristica.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;

public class NovaCaracteristicaRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NovaCaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
