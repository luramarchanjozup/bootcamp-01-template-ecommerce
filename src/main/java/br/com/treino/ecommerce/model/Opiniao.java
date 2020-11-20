package br.com.treino.ecommerce.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Min(1) @Max(5) int nota;
    private @NotBlank @Length(max=500) String descricao;
    private @NotBlank String titulo;
    @ManyToOne
    private @NotNull @Valid Produto produto;
    @ManyToOne
    private @NotNull @Valid Usuario usuario;

    @Deprecated
    public Opiniao(){}

    public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String descricao, @NotBlank String titulo,
                   @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario usuario) {
        this.nota = nota;
        this.descricao = descricao;
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", descricao='" + descricao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
