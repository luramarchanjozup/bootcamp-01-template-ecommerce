package br.com.ecommerce.adicionarrespostaTestes;


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
public class AdicionarRespostaDeveRetornarBadRequest {

    @LocalServerPort
    private int port;

    @Value("${ecommerce.jwt.testes}")
    private String token;

    @Test
    public void deveRetornarBadRequestQuandoConteudoEhNulo() throws JSONException {


        JSONObject resposta = new JSONObject()
                .put("conteudo"," ")
                .put("perguntaId",1);


        given()
                .basePath("/produtos/1/resposta")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(resposta.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());


    }


}
