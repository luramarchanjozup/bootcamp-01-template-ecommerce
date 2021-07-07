package br.com.ecommerce.finalizacompraTestes;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class ProcessaPagamentoComSucesso {


    @LocalServerPort
    private int port;


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @Test
    public void deveRetornarUrlPaypalAoProcessarPagamento() throws JSONException {


        JSONObject processaPagamento = new JSONObject()
                .put("status",1)
                .put("transacaoId",1);


        given()
                .basePath("/retorno-paypal/55")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(processaPagamento.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveRetornarUrlPagseguroAoProcessarPagamento() throws JSONException {




        JSONObject processaPagamento = new JSONObject()
                .put("status",1)
                .put("transacaoId",1);


        given()
                .basePath("/retorno-pagseguro/51")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(processaPagamento.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }
}
