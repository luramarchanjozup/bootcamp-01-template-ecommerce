package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.api.handler.Unique;
import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.entity.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RequestPaypalDto implements RequestCheckoutPagamento {

    @NotBlank @Unique(domainClass = Transacao.class, fieldName = "idTransacaoGateway")
    private final String idTransacao;

    @Min(0) @Max(1)
    private final int status;

    public RequestPaypalDto(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Override
    public Compra toTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        var transacao = new Transacao(statusCalculado, idTransacao, compra);
        compra.setTransacao(transacao);
        return compra;
    }
}

