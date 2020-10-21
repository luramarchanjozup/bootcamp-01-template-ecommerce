package io.github.evertoncnsouza.rest.dto;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoProduto;

    public RankingNovaCompraRequest(@NotNull Long idCompra,
                                    @NotNull Long idDonoProduto) {
        super();
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }
    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingNovaCompraRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }
}

