package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;
import com.github.marcoscoutozup.ecommerce.validator.transacao.Transacao;
import jdk.jshell.Snippet;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PaypalDTO implements TentativaDePagamentoInterface {

    @NotNull
    @Transacao
    private UUID transacao;

    @NotNull //1
    @Max(1)
    @Min(0)
    private Integer statusPagamento;

    @Override
    public TentativaDePagamento toTentativaDePagamento() {
        StatusPagamento statusPagamento = this.statusPagamento == 0 ? StatusPagamento.FALHA : StatusPagamento.SUCESSO;
        return new TentativaDePagamento(transacao, statusPagamento);
    }

    public UUID getTransacao() {
        return transacao;
    }

    public void setTransacao(UUID transacao) {
        this.transacao = transacao;
    }

    public Integer getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(Integer statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
