package br.com.zup.ecommerce.entities.compra.transacao;

import br.com.zup.ecommerce.entities.compra.Compra;

public interface RetornoTransacaoRequest {

    Transacao toModel(Compra compra);
}
