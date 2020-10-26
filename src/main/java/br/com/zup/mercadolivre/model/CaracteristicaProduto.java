package br.com.zup.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name="caracteristicaProduto")
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
