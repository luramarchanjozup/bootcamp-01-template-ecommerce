package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;
import com.github.marcoscoutozup.ecommerce.validator.transacao.Transacao;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TentativaDePagamentoDTO {

    @NotNull
    @Transacao
    private UUID transacao;

    @NotNull //1
    private StatusPagamento statusPagamento;

                //2
    public TentativaDePagamento toModel(){
        return new TentativaDePagamento(transacao, statusPagamento);
    }

    public UUID getTransacao() {
        return transacao;
    }

    public void setTransacao(UUID transacao) {
        this.transacao = transacao;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }


}
