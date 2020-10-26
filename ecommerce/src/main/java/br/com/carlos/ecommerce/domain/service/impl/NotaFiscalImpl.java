package br.com.carlos.ecommerce.domain.service.impl;

import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.service.EventoCompraSucessoService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscalImpl implements EventoCompraSucessoService {
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"Falha ao processar a compra "+compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getComprador().getId());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
                request, String.class);
    }
}
