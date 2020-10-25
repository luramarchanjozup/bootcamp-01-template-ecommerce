package br.com.ecommerce.finalizacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {


    private Long transacaoId;


    private StatusRetornoPagseguro status;


    public RetornoPagseguroRequest(@NotBlank Long transacaoId, @NotNull StatusRetornoPagseguro status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(Compra compra) {

        StatusTransacao statusNormalizado = status.normaliza();

        return new Transacao(statusNormalizado, transacaoId, compra);

    }

}
