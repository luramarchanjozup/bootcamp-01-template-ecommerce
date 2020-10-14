package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public class Paypal implements Pagamento{

    @Override
    public String retornarUrlDePagamento(UUID id, String urlBase) {
        return "paypal.com/" + id + "?redirectUrl=" + urlBase + "/pagamento/" + id;
    }
}
