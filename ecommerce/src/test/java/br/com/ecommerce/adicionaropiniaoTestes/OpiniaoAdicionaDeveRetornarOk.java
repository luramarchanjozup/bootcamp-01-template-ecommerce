package br.com.ecommerce.adicionaropiniaoTestes;


import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
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

import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class OpiniaoAdicionaDeveRetornarOk {


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @LocalServerPort
    private int port;


    @Test
    public void deveRetornarOkQuandoOpiniaoAdicionaComSucesso() throws JSONException {


        JSONObject opiniao = new JSONObject()
                .put("nota",2)
                .put("titulo","teste")
                .put("descricao","descrição teste")
                .put("usuarioId",1)
                .put("produtoId",1);


        given()
                .basePath("/opinioes")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", this.token)
                .body(opiniao.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }
}
