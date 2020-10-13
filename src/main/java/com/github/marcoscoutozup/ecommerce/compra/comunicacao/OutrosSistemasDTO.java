package com.github.marcoscoutozup.ecommerce.compra.comunicacao;

import java.util.UUID;

public class OutrosSistemasDTO {

    private UUID idCompra;
    private UUID idUsuario;

    public OutrosSistemasDTO(UUID idCompra, UUID idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    public UUID getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(UUID idCompra) {
        this.idCompra = idCompra;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
