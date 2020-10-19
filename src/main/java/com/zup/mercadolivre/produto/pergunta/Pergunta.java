package com.zup.mercadolivre.produto.pergunta;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    private @ManyToOne @NotNull @Valid Usuario perguntante;
    private @ManyToOne @NotNull @Valid Produto produto;
    private LocalDateTime instante = LocalDateTime.now();

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

    public Usuario getPerguntante() {
        return perguntante;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }
}
