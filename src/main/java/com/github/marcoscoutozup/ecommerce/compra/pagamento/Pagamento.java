package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public interface Pagamento {

    String retornarUrlDePagamento(UUID id);

}
