package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.Compra;

public interface RequestCheckoutPagamento {
    Compra toTransacao(Compra compra);
}
