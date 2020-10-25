package br.com.ecommerce.finalizacompraTestes;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompraSemSucesso {



    @Value("${ecommerce.jwt.testes}")
    private String token;


    @LocalServerPort
    private int port;


    @Test
    public void compraDeveRetornarBadRequestSeTiverComDadosErrados() throws JSONException {

        JSONObject compra = new JSONObject()
                .put("quantidade",30)
                .put("gatewayPagamento","dado errado")
                .put("produtoId", 1);


        given()
                .basePath("/produtos/1/compras")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(compra.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

}
