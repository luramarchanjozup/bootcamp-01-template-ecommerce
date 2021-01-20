package br.com.zup.ecomerce.nicolle.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import br.com.zup.ecomerce.nicolle.model.Compra;

@Service
public class Ranking implements EventoCompraSucesso{
	
	
	public void processaCompra(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(), "Essa compra não foi concluida com sucesso!");
		RestTemplate template = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getVendedor().getId());
		template.postForEntity("http://localhost:8080/ranking", request, String.class);
		
	}

}
