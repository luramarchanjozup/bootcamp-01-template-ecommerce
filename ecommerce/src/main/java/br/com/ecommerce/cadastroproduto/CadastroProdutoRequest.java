package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.cadastrocategoria.Categoria;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Long quantidadeDisponivel;

    @ElementCollection
    @Size(min = 3)
    private List<Caracteristica> caracteristicas;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    private Long categoriaId;

    public CadastroProdutoRequest(@NotBlank String nome, @NotBlank @Positive BigDecimal valor, @NotBlank @Positive Long quantidadeDisponivel,
                                  @Size(min = 3) List<Caracteristica> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
                                  @NotNull Long categoriaId) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;

    }

    public Produto converteParaTipoProduto(EntityManager entityManager) {

        Categoria categoria = entityManager.find(Categoria.class, categoriaId);

        return new Produto(nome, valor, quantidadeDisponivel, caracteristicas, descricao, categoria);

    }


}
