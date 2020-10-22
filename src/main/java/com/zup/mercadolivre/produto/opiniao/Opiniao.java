package com.zup.mercadolivre.produto.opiniao;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Min(1) @Max(5) @NotNull int nota;
    private @NotBlank String titulo;
    private @Size(max = 500) @NotBlank String descricao;
    private @ManyToOne @NotNull @Valid Produto produto;
    private @ManyToOne @NotNull @Valid Usuario consumidor;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo,
                   @Size(max = 500) @NotBlank String descricao, @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", consumidor=" + consumidor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return Objects.equals(titulo, opiniao.titulo) &&
                Objects.equals(descricao, opiniao.descricao) &&
                Objects.equals(produto, opiniao.produto) &&
                Objects.equals(consumidor, opiniao.consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, produto, consumidor);
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
}
