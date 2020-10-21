package io.github.evertoncnsouza.rest.dto;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idComprador;

    public NovaCompraNFRequest(@NotNull Long idCompra, @NotNull Long idComprador) {
        super();
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "CompraNFRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }

}
