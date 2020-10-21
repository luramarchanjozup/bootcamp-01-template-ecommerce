package io.github.evertoncnsouza.domain.entity;

import io.github.evertoncnsouza.domain.enums.StatusTransacao;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

//3 PCI's
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

     @ManyToOne
     @NotNull
     @Valid
     private Compra compra;

    @Deprecated
    public Transacao() {

    }

    public Transacao(@NotNull StatusTransacao status,
                     @NotBlank String idTransacaoGateway, @NotNull @Valid Compra compra
    ) {
       this.status = status;
       this.idTransacaoGateway = idTransacaoGateway;
       this.compra = compra;
       this.instante = LocalDateTime.now();
    }


    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public String toString() {
        return "Transacao [id=" + id + ", status=" + status
                + ", idTransacaoGateway=" + idTransacaoGateway + ", instante="
                + instante + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }
}