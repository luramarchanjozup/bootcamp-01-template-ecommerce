package br.com.ecommerce.finalizacompra;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {


        Assert.isTrue(compra.processadaComSucesso(),"opa opa opa compra nao processada com sucesso " + compra);


        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idDonoProduto", compra.getUsuario().getId());


       /*
            A requisição tá recebendo forbidden por causa do spring security

        * */


        WebClient client = WebClient.create("http://localhost:8080");


        Mono<Void> result = client.post()
                .uri("/notas-fiscais")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);


        result
                .then()
                .doOnNext(x -> System.out.println("OK"))
                .subscribe();

    }
}
