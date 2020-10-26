package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.api.handler.Unique;
import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.entity.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestPagseguroDto implements RequestCheckoutPagamento {
    @NotBlank @Unique(domainClass = Transacao.class, fieldName = "idTransacaoGateway")
    private final String idTransacao;
    @NotNull
    private final StatusRetornoPagseguro status;

    public RequestPagseguroDto(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Compra toTransacao(Compra compra) {
        var transacao = new Transacao(status.normaliza(), idTransacao, compra);
        compra.setTransacao(transacao);
        return compra;
    }
}
