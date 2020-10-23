package com.zup.mercadolivre.produto.pergunta;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    private @ManyToOne @NotNull @Valid Usuario perguntante;
    private @ManyToOne @NotNull @Valid Produto produto;
    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario perguntante, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.perguntante = perguntante;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", consumidor=" + perguntante +
                ", produto=" + produto +
                ", instante=" + instante +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getPerguntante() {
        return perguntante;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(titulo, pergunta.titulo) &&
                Objects.equals(perguntante, pergunta.perguntante) &&
                Objects.equals(produto, pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, perguntante, produto);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
