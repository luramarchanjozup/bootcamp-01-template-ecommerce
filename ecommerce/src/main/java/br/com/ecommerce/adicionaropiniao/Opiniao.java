package br.com.ecommerce.adicionaropiniao;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Max(5) @Min(1)
    private Double nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    /* @complexidade = acoplamento contextual */
    @NotNull
    @ManyToOne
    private Usuario usuario;

    /* @complexidade = acoplamento contextual */
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {};

    public Opiniao(@NotBlank Double nota, @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario,
                   @NotNull Produto produto) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Double getNota() {
        return nota;
    }


    public String getTitulo() {
        return titulo;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
