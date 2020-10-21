package br.com.zup.ecommerce.entities.produto.opiniao;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500, message = "deve ter tamanho igual ou menor que 500")
    private String descricao;

    @NotNull
    @ManyToOne
    //1
    private Usuario usuario;

    @NotNull
    @ManyToOne
    //1
    private Produto produto;

    @Deprecated
    public OpiniaoProduto(){}

    public OpiniaoProduto(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500, message = "deve ter tamanho igual ou menor que 500") String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public OpiniaoProduto(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500, message = "deve ter tamanho igual ou menor que 500") String descricao, @NotNull Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
