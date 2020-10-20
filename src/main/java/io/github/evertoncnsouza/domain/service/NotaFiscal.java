package io.github.evertoncnsouza.domain.service;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.repository.EventoCompraSucesso;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Compra não concluída com sucesso"+compra );

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getNavegador().getId());

        restTemplate.postForEntity("http://localhost:8080/notasfiscais", request,
                String.class);
    }
}
