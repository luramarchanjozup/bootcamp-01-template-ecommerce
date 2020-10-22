package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.enums.GatewayPagamento;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

//1 PCI
public class CompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    public CompraRequest(@Positive int quantidade, @NotNull Long idProduto,
                         GatewayPagamento gateway) {
        super();
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
