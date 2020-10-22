package com.zup.mercadolivre.compra;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return retorna uma nova transação em função do gateway escolhido
     */
    Transacao toTransacao(Compra compra);
}
