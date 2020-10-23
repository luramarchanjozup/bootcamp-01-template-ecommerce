package com.zup.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    @Override
    public String toString() {
        return "RankingNovaCompraRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }

    public RankingNovaCompraRequest(@NotNull Long idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }

    public void setIdDonoProduto(Long idDonoProduto) {
        this.idDonoProduto = idDonoProduto;
    }
}
