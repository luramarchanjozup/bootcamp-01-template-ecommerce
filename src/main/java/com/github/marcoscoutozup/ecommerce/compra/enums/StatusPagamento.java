package com.github.marcoscoutozup.ecommerce.compra.enums;

public enum StatusPagamento {

    FALHA(0),
    SUCESSO(1);

    private Integer codigo;

    StatusPagamento(Integer codigo) {
        this.codigo = codigo;
    }
}
