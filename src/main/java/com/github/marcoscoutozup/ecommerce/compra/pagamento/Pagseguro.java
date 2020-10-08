package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public class Pagseguro implements Pagamento{

    @Override
    public String retornarUrlDePagamento(UUID id) {
        return "pagseguro.com/?returnId=" + id + "&redirectUrl={urlRetornoAppPosPagamento}";
    }
}
