package io.github.evertoncnsouza.rest.controller;


import io.github.evertoncnsouza.domain.entity.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest {

    @NotBlank
    private String idTransacao;

    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagSeguroRequest(@NotBlank String idTransacao,
                                   StatusRetornoPagseguro status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
