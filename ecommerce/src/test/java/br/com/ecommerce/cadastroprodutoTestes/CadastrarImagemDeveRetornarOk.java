package br.com.ecommerce.cadastroprodutoTestes;


import org.json.JSONArray;
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

import java.io.File;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class CadastrarImagemDeveRetornarOk {


    @LocalServerPort
    private int port;


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @Test
    public void deveRetornaroKQuandoCadastrarImagem() {

        given().
                basePath("/imagens/1")
                .port(port)
                .header("Authorization", token)
                .multiPart("arquivos", new File("/home/marceloamorim/Imagens/testes.jpg"))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }
}
