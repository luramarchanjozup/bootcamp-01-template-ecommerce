package br.com.zup.ecommerce.entities.produto.caracteristica;

import br.com.zup.ecommerce.entities.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracteristicasProdutoNovoRequest)) return false;
        CaracteristicasProdutoNovoRequest that = (CaracteristicasProdutoNovoRequest) o;
        return nome.equals(that.nome) &&
                descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    //2
    public CaracteristicasProduto toModel(Produto produto) {
        return new CaracteristicasProduto(this.nome, this.descricao, produto);
    }

    public CaracteristicasProduto toModelSemProduto() {
        return new CaracteristicasProduto(this.nome, this.descricao);
    }
}
