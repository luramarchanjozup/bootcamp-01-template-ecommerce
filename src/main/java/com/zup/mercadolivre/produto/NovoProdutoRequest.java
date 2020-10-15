package com.zup.mercadolivre.produto;

import com.zup.mercadolivre.categoria.Categoria;
import com.zup.mercadolivre.compartilhado.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovoProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @Positive
    private int quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                              @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
                              @NotNull Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria);
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}