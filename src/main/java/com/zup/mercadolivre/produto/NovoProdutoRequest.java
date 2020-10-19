package com.zup.mercadolivre.produto;

import com.zup.mercadolivre.categoria.Categoria;
import com.zup.mercadolivre.compartilhado.ExistsId;
import com.zup.mercadolivre.compartilhado.UniqueValue;
import com.zup.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoRequest {
    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
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
    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                              @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
                              @NotNull Long idCategoria, List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(EntityManager manager, Usuario dono) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria, dono, caracteristicas);
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}