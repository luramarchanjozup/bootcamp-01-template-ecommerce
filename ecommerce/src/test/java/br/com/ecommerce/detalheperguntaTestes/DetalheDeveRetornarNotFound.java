package br.com.ecommerce.detalheperguntaTestes;


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

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class DetalheDeveRetornarNotFound {

    @Value("${ecommerce.jwt.testes}")
    private String token;

    @LocalServerPort
    private int port;


    @Test
    public void deveRetornarNotFoundQuandoIdNaoExiste() throws JSONException {


        given()
                .basePath("/detalhe/100123123")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());


    }
}
