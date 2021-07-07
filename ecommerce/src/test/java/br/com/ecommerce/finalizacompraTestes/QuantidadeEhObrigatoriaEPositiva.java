package br.com.ecommerce.finalizacompraTestes;

import br.com.ecommerce.finalizacompra.Compra;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class QuantidadeEhObrigatoriaEPositiva {

    @LocalServerPort
    private int port;

    @Value("${ecommerce.jwt.testes}")
    private String token;

    @Test
    public void quantidadeDeveDoProdutoCompradoDeveSerPositiva() throws JSONException {

        JSONObject compra = new JSONObject()
                .put("quantidade",-10)
                .put("gatewayPagamento","paypal")
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
