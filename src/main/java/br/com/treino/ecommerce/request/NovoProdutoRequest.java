package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Caracteristica;
import br.com.treino.ecommerce.model.Categoria;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.shared.validations.ExisteValor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class NovoProdutoRequest {

    private @NotBlank String nome;
    private @Positive BigDecimal valor;
    private @Min(1) Integer quantidade;
    @ExisteValor(nomeClasse = Categoria.class, nomeCampo = "id") //1
    private @NotNull Long idCategoria;
    @Length(max = 1000)
    private @NotBlank String descricao;
    @NotNull @Size(min = 3)
    private List<NovaCaracteristicaRequest> novaCaracteristica = new ArrayList<>(); //2

    @Deprecated
    public NovoProdutoRequest(){}

     public NovoProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor,
                              @Min(1) Integer quantidade, @NotNull Long idCategoria,
                              @Length(max = 1000) @NotBlank String descricao,
                              @NotNull @Size(min = 3) List<NovaCaracteristicaRequest>
                                       novaCaracteristica) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.novaCaracteristica = novaCaracteristica;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return novaCaracteristica;
    }

    public Produto toProduto(EntityManager entityManager, Usuario usuario){ //3 //4

        @NotNull Categoria categoria = entityManager.find(Categoria.class,
                this.idCategoria); //5

        List<Caracteristica> listaCaracteriticas = new ArrayList(); //6

        for(NovaCaracteristicaRequest request : novaCaracteristica){ //7
            Caracteristica caracteristica = request.toCaracteristica();
            listaCaracteriticas.add(caracteristica);
        }

        return new Produto(this.nome, this.valor, this.quantidade, categoria,
                this.descricao, listaCaracteriticas, usuario);
    }


}
