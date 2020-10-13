package io.github.evertoncnsouza.domain.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

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

    private LocalDateTime instanteCriacao;

    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Produto produto,
                    @NotNull @Valid User navegador,
                    LocalDateTime instanteCriacao) {
        this.titulo = titulo;
        this.produto = produto;
        this.navegador = navegador;
        this.instanteCriacao = instanteCriacao;
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
