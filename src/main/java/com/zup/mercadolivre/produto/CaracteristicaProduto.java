package com.zup.mercadolivre.produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class CaracteristicaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotBlank String descricao;
    private @ManyToOne @NotNull @Valid Produto produto;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao,
                                 @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "CaracteristicaProduto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaProduto that = (CaracteristicaProduto) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
