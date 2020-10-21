package br.com.treino.ecommerce.model;

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
    private @NotBlank String mensagem;
    @ManyToOne
    private @NotNull @Valid Produto produto; //1
    @ManyToOne
    private @NotNull @Valid Usuario usuario; //2
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Pergunta(){}

    public Pergunta(String mensagem, Produto produto, Usuario usuario) {
        this.mensagem = mensagem;
        this.produto = produto;
        this.usuario = usuario;
        this.instanteCriacao = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", mensagem='" + mensagem + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", localDateTime=" + instanteCriacao +
                '}';
    }

}
