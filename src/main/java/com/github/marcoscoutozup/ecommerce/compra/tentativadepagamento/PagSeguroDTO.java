package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;
import com.github.marcoscoutozup.ecommerce.validator.transacao.Transacao;
import io.jsonwebtoken.lang.Assert;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PagSeguroDTO implements TentativaDePagamentoInterface{

    @NotNull
    @Transacao
    private UUID transacao;

    @NotNull
    private String statusPagamento;

    @Override //1
    public TentativaDePagamento toTentativaDePagamento() {
        Assert.isTrue(statusPagamento.equals("SUCESSO") || statusPagamento.equals("FALHA"), "O status de pagamento é inválido");
        //2
        StatusPagamento statusPagamento;
        //3
        if(this.statusPagamento.equals("SUCESSO")){
            return new TentativaDePagamento(transacao,  StatusPagamento.SUCESSO);
        }

        return new TentativaDePagamento(transacao, StatusPagamento.FALHA);
    }

    public UUID getTransacao() {
        return transacao;
    }

    public void setTransacao(UUID transacao) {
        this.transacao = transacao;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

}
