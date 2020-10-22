package io.github.evertoncnsouza.domain.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

//Não tem PCI
@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @ManyToOne
    @NotNull
    @Valid
    Usuario navegador;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) int nota,
                   @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao,
                   @NotNull @Valid Produto produto, @NotNull @Valid Usuario navegador) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.navegador = navegador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opiniao)) return false;
        Opiniao opiniao = (Opiniao) o;
        return getNota() == opiniao.getNota() &&
                getTitulo().equals(opiniao.getTitulo()) &&
                Objects.equals(getDescricao(), opiniao.getDescricao()) &&
                produto.equals(opiniao.produto) &&
                navegador.equals(opiniao.navegador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNota(), getTitulo(), getDescricao(), produto, navegador);
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", navegador=" + navegador +
                '}';
    }
}
