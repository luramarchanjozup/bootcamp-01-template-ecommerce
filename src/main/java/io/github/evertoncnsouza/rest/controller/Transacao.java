package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacaoGateway;

    @NotNull
    private LocalDateTime instante;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status,
                     @NotBlank String idTransacaoGateway,
                                @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }



    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", instante=" + instante +
                        '}';
    }
}
