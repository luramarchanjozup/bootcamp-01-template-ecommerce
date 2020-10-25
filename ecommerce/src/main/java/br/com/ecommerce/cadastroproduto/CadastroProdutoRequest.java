package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicasCadastro = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long usuarioId;

    public CadastroProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidadeDisponivel,
                                  @Size(min = 3) List<CaracteristicaRequest> caracteristicasCadastro, @NotBlank @Size(max = 1000) String descricao, @NotNull Long categoriaId, @NotNull Long usuarioId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicasCadastro = caracteristicasCadastro;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.usuarioId = usuarioId;
    }

    public Produto converteParaTipoProduto(EntityManager entityManager) {


        Categoria categoria = entityManager.find(Categoria.class, categoriaId);

        Usuario usuario = entityManager.find(Usuario.class, usuarioId);


        return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.caracteristicasCadastro, this.descricao, categoria, usuario);


    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
