package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.model.CaracteristicaProduto;
import br.com.zup.mercadolivre.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaProdutoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaProdutoRequestDTO(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel (@Valid @NotNull Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
