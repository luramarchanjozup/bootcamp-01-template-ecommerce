package br.com.zup.ecommerce.entities.produto.caracteristica;

import javax.validation.constraints.NotBlank;

/**
 * Contagem de carga intrínseca da classe: 1
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

    //1
    public CaracteristicasProduto toModelSemProduto() {
        return new CaracteristicasProduto(this.nome, this.descricao);
    }
}
