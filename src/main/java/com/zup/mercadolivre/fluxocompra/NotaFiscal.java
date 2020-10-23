package com.zup.mercadolivre.fluxocompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),
                "A compra não foi concluída, portanto não pode ser processada. " + compra);
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
                request, String.class);
    }
}
