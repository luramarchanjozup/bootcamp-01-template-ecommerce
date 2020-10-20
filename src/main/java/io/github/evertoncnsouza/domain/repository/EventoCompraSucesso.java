package io.github.evertoncnsouza.domain.repository;

import io.github.evertoncnsouza.domain.entity.Compra;

public interface EventoCompraSucesso {

    void processa(Compra compra);
}
