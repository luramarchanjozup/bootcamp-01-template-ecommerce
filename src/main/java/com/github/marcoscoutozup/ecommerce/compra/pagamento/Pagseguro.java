package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public class Pagseguro implements Pagamento{

    @Override
    public String retornarUrlDePagamento(UUID id, String urlBase) {
        return "pagseguro.com/?returnId=" + id + "&redirectUrl=" + urlBase + "/pagamento/" + id;
    }
}
