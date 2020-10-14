package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Categoria;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.validation.constraintvalidation.ExistsId;
import io.github.evertoncnsouza.validation.constraintvalidation.UniqueValue;
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

//PCI 6
public class ProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Positive
    private int quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(@NotBlank String nome,
                          @NotNull @Positive int quantidade,
                          @NotBlank @Length(max = 1000) String descricao,
                          @NotNull @Positive BigDecimal valor,
                          @NotNull Long idCategoria,
                          @Size(min = 3) @Valid List<CaracteristicaRequest> caracteristicas) {
        super();
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(
            List<CaracteristicaRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (CaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();

            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
    public Produto toModel(EntityManager manager, User dono){
    Categoria categoria = manager.find(Categoria.class, idCategoria);
    return new Produto(nome, quantidade, descricao,valor, categoria, dono, caracteristicas);
}

}