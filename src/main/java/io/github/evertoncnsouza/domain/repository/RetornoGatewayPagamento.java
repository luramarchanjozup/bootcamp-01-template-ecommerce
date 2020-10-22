package io.github.evertoncnsouza.domain.repository;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.entity.Transacao;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
