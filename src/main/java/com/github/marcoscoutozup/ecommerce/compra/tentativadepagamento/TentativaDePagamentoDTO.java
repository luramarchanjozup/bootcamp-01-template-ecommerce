package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TentativaDePagamentoDTO {

    @NotNull
    private UUID transacao;

    @NotNull //1
    private StatusPagamento status;

    public TentativaDePagamento toModel(){
        return new TentativaDePagamento(transacao, status);
    }

    public UUID getTransacao() {
        return transacao;
    }

    public void setTransacao(UUID transacao) {
        this.transacao = transacao;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }


}
