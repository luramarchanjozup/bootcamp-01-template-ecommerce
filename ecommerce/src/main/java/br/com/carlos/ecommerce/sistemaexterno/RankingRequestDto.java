package br.com.carlos.ecommerce.sistemaexterno;

import javax.validation.constraints.NotNull;

public class RankingRequestDto {
    @NotNull
    private final Long idCompra;
    @NotNull
    private final Long idDonoProduto;

    public RankingRequestDto(@NotNull Long idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingRequestDto{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }
}
