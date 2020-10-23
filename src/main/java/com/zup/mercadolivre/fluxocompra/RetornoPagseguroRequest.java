package com.zup.mercadolivre.fluxocompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {
    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public StatusRetornoPagseguro getStatus() {
        return status;
    }

    public void setStatus(StatusRetornoPagseguro status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}