package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.CaracteristicaProduto;
import io.github.evertoncnsouza.domain.entity.Produto;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//2 PCI's
public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome,
                                 @NotBlank String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
