package br.com.zup.ecommerce.entities.produto.pergunta;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String titulo;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @NotNull
    @Valid
    @ManyToOne
    //1
    private Usuario usuario;
    @NotNull
    @Valid
    @ManyToOne
    //1
    private Produto produto;

    @Deprecated
    private PerguntaProduto(){}

    public PerguntaProduto(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"titulo\": \"" + titulo + "\"" +
                ", \"dataCadastro\": \"" + dataCadastro + "\"" +
                '}';
    }
}
