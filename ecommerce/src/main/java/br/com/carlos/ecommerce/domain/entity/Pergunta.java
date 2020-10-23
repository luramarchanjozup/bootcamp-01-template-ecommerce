package br.com.carlos.ecommerce.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "perguntas")
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @ManyToOne
    @Valid @NotNull
    private Produto produto;

    @ManyToOne
    @Valid @NotNull
    private Usuario interessado;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @Deprecated
    public Pergunta(){}

    public Pergunta(@NotBlank String titulo, @Valid @NotNull Produto produto, @Valid @NotNull Usuario interessado) {
        this.titulo = titulo;
        this.produto = produto;
        this.interessado = interessado;
    }


    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getInteressado() {
        return interessado;
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }

}
