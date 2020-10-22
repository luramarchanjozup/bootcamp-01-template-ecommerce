package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.CaracteristicaProduto;
import br.com.carlos.ecommerce.domain.entity.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class RequestCaracteristicaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public RequestCaracteristicaDto(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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


    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(nome,descricao,produto);
    }

}
