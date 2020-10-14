package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TentativaDePagamento {

    @NotNull
    private UUID transacao;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusPagamento status;

    private LocalDateTime instanteDaTransacao;

    @Deprecated
    public TentativaDePagamento() {
    }

    public TentativaDePagamento(UUID transacao, StatusPagamento status) {
        this.transacao = transacao;
        this.status = status;
        this.instanteDaTransacao = LocalDateTime.now();
    }

    public UUID getTransacao() {
        return transacao;
    }

    public boolean transacaoFoiUmSucesso(){
        return status.equals(StatusPagamento.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TentativaDePagamento that = (TentativaDePagamento) o;
        return Objects.equals(transacao, that.transacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacao);
    }
}
