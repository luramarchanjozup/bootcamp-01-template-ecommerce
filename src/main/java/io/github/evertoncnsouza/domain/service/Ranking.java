package io.github.evertoncnsouza.domain.service;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class Ranking  {

    @Autowired
    private RestTemplate restTemplate;

    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"Compra não processada com sucesso"+compra);

        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idDonoProduto", compra.getDonoProduto().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking",
                request, String.class);
    }
}
