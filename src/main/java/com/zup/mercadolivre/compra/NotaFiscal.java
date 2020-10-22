package com.zup.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal {
    @Autowired
    private RestTemplate restTemplate;

    public void processa(Compra compra) {
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
