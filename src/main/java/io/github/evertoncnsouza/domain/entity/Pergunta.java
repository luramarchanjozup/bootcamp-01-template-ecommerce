package io.github.evertoncnsouza.domain.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable <Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @ManyToOne
    @NotNull
    @Valid
    Produto produto;

    @ManyToOne
    @NotNull
    @Valid
    User navegador;

    private LocalDate instanteCriacao;

    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Produto produto,
                    @NotNull @Valid User navegador,
                    LocalDateTime instanteCriacao) {
        this.titulo = titulo;
        this.produto = produto;
        this.navegador = navegador;
        this.instanteCriacao = LocalDate.now();
    }

    public User getNavegador() {
        return navegador;
    }

    public User getDono() {
        return produto.getDono();
    }

    public String getTitulo() {
        return titulo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pergunta)) return false;
        Pergunta pergunta = (Pergunta) o;
        return titulo.equals(pergunta.titulo) &&
                produto.equals(pergunta.produto) &&
                getNavegador().equals(pergunta.getNavegador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produto, getNavegador());
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", produto=" + produto +
                ", navegador=" + navegador +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }
}