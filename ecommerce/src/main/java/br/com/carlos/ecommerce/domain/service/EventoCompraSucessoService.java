package br.com.carlos.ecommerce.domain.service;

import br.com.carlos.ecommerce.domain.entity.Compra;

public interface EventoCompraSucessoService {
    void processa(Compra compra);

}
