package io.github.evertoncnsouza.rest.dto;


import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.repository.RetornoGatewayPagamento;
import io.github.evertoncnsouza.domain.enums.StatusRetornoPagseguro;
import io.github.evertoncnsouza.domain.entity.Transacao;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

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
