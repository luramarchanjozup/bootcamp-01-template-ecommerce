package com.zup.mercadolivre.produto;

import com.zup.mercadolivre.categoria.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotNull @Positive BigDecimal valor;
    private @Positive int quantidade;
    private @NotBlank @Length(max = 1000) String descricao;
    private @ManyToOne @NotNull @Valid Categoria categoria;

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
