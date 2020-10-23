package com.zup.mercadolivre.fluxocompra;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return retorna uma nova transação em função do gateway escolhido
     */
    Transacao toTransacao(Compra compra);
}
