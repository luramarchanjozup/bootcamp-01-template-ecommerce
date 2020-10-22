package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.api.handler.ExistsById;
import br.com.carlos.ecommerce.domain.entity.Categoria;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.entity.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

public class RequestProdutoDto {
    
    @NotBlank
    private String nome;

    @NotBlank @Length(max = 1000)
    private String descricao;

    @Positive
    private int quantidade;

    @Positive
    private BigDecimal valor;

    @NotNull
    @ExistsById(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @Size(min = 3, message = "quantidade mínina é 3")
    private List<RequestCaracteristicaDto> caracteristicas = new ArrayList<>();


    public RequestProdutoDto(@NotBlank String nome, @NotBlank @Length(max = 1000) String descricao,
                             @Positive int quantidade, @Positive BigDecimal valor, @NotBlank Long idCategoria,
                             @Size(min = 3) List<RequestCaracteristicaDto> caracteristicas) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(EntityManager manager, Usuario comprador) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, descricao, quantidade, valor, categoria,
                caracteristicas, comprador);
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        // 1
        for (RequestCaracteristicaDto caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            // 1
            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }

}
