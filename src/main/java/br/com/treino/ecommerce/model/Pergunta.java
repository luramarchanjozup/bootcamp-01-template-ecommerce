package br.com.treino.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private @NotNull @Valid Usuario interessado; //2
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Pergunta(){}

    public Pergunta(String mensagem, Produto produto, Usuario usuario) {
        this.mensagem = mensagem;
        this.produto = produto;
        this.interessado = usuario;
        this.instanteCriacao = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    @JsonIgnore
    public Produto getProduto() {
        return produto;
    }

    public Usuario getInteressado() {
        return interessado;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", mensagem='" + mensagem + '\'' +
                ", usuario=" + interessado +
                '}';
    }

}
