package br.com.ecommerce.finalizacompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;


    private Long transacaoId;


    public RetornoPaypalRequest(int status, Long transacaoId) {
        this.status = status;
        this.transacaoId = transacaoId;
    }


    @Override
    public Transacao toTransacao(Compra compra) {

        var statusNumeroZeroOuUm = this.status;

        StatusTransacao statusDaTransacao;

        if(statusNumeroZeroOuUm == 0){

            statusDaTransacao  = StatusTransacao.erro;

        }else {

            statusDaTransacao =  StatusTransacao.sucesso;

        }

        return new Transacao(statusDaTransacao, transacaoId, compra);

    }
}
