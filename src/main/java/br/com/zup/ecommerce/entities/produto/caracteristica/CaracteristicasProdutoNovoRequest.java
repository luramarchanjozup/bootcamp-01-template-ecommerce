package br.com.zup.ecommerce.entities.produto.caracteristica;

import br.com.zup.ecommerce.entities.produto.Produto;

import javax.validation.constraints.NotBlank;

/**
 * Contagem de carga intrínseca da classe: 2
 */

public class CaracteristicasProdutoNovoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    //2
    public CaracteristicasProduto toModel(Produto produto) {
        return new CaracteristicasProduto(this.nome, this.descricao, produto);
    }
}
