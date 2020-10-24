package br.com.ecommerce.fazerperguntaTestes;


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
public class FazerPerguntaDeveRetornarOk {


    @LocalServerPort
    private int port;

    @Value("${ecommerce.jwt.testes}")
    private String token;

    @Test
    public void deveRetornarOkQuandoPerguntaForAdicionaComSucesso() throws JSONException {


        JSONObject pergunta = new JSONObject()
                .put("titulo","teste")
                .put("usuarioId",1)
                .put("produtoId", 1);


        given()
                .basePath("/pergunta")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(pergunta.toString())
                    .when()
                .post()
                    .then()
                .statusCode(HttpStatus.OK.value());

    }
}
