package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public class Paypal implements Pagamento{

    @Override
    public String retornarUrlDePagamento(UUID id, String url_base) {
        return "paypal.com/" + id + "?redirectUrl=" + url_base + "/pagamento/" + id;
    }
}
