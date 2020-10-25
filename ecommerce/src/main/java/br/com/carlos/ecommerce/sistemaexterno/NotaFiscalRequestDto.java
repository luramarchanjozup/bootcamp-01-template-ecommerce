package br.com.carlos.ecommerce.sistemaexterno;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequestDto {

    @NotNull
    private final Long idCompra;
    @NotNull
    private final Long idComprador;

    public NotaFiscalRequestDto(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequestDto{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
