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

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompraComSucesso {

    @Value("${ecommerce.jwt.testes}")
    private String token;


    @LocalServerPort
    private int port;


    @Test
    public void compra1DeveRetornarOk() throws JSONException {

        JSONObject compra = new JSONObject()
                .put("quantidade",30)
                .put("gatewayPagamento","paypal")
                .put("produtoId", 90);


        given()
                .basePath("/produtos/90/compras")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(compra.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }


}
