package br.com.zup.ecommerce.entities.produto.caracteristica;

import br.com.zup.ecommerce.entities.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Contagem de carga intrínseca da classe: 1
 */

@Entity
public class CaracteristicasProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    //1
    private Produto produto;

    @Deprecated
    public CaracteristicasProduto(){}

    public CaracteristicasProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public CaracteristicasProduto(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracteristicasProduto)) return false;
        CaracteristicasProduto that = (CaracteristicasProduto) o;
        return nome.equals(that.nome) &&
                descricao.equals(that.descricao) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, produto);
    }

}
