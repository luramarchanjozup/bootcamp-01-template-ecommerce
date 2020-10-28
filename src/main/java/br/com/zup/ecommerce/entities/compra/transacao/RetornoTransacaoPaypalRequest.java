package br.com.zup.ecommerce.entities.compra.transacao;

import br.com.zup.ecommerce.entities.compra.Compra;
import br.com.zup.ecommerce.validations.valorUnico.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Contagem de carga intrínseca da classe: 5
 */

public class RetornoTransacaoPaypalRequest implements RetornoTransacaoRequest {

    @NotBlank
    //2
    @ValorUnico(dominioClasse = Transacao.class, nomeCampo = "idTransacao")
    private String idTransacao;

    @NotNull
    private int statusTransacao;

    public RetornoTransacaoPaypalRequest(@NotBlank String idTransacao, @NotNull int statusTransacao) {
        this.idTransacao = idTransacao;
        this.statusTransacao = statusTransacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public int getStatusTransacao() {
        return statusTransacao;
    }


    @Override
    //2
    public Transacao toModel(Compra compra) {

        StatusTransacaoEnum status = StatusTransacaoEnum.SUCESSO;

        //1
        if (this.statusTransacao == 0) {
            status = StatusTransacaoEnum.ERRO;
        }

        return new Transacao(idTransacao, status, compra);
    }
}
