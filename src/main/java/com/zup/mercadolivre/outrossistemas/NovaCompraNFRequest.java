package com.zup.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    @Override
    public String toString() {
        return "NovaCompraNFRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }

    public NovaCompraNFRequest(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Long idComprador) {
        this.idComprador = idComprador;
    }
}
