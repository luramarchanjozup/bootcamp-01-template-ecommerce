package br.com.zup.ecommerce.entities.compra.pagamento;

import br.com.zup.ecommerce.entities.compra.Compra;
import br.com.zup.ecommerce.validations.valorUnico.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class RetornoPagamentoPaypalRequest {

    @NotBlank
    //2
    @ValorUnico(dominioClasse = Pagamento.class, nomeCampo = "idTransacao")
    private String idTransacao;

    @NotNull
    private int statusPagamento;

    public RetornoPagamentoPaypalRequest(@NotBlank String idTransacao, @NotNull int statusPagamento) {
        this.idTransacao = idTransacao;
        this.statusPagamento = statusPagamento;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public int getStatusPagamento() {
        return statusPagamento;
    }

    //2
    public Pagamento toModel(Compra compra) {

        StatusTransacaoEnum status = StatusTransacaoEnum.SUCESSO;

        //1
        if (this.statusPagamento == 0) {
            status = StatusTransacaoEnum.ERRO;
        }

        return new Pagamento(idTransacao, status, compra);
    }
}
