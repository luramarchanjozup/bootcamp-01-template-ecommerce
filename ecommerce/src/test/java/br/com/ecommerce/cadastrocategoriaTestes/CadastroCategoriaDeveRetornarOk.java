package br.com.ecommerce.cadastrocategoriaTestes;


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

import java.nio.charset.Charset;
import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class CadastroCategoriaDeveRetornarOk {

    @LocalServerPort
    private int port;


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @Test
    public void deveRetornaroKQuandoCadastrarCategoria() throws JSONException {

        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String stringAleatoria = new String(array, Charset.forName("UTF-8"));


        JSONObject categoria = new JSONObject()
                .put("nome",stringAleatoria)
                .put("categoriaId", 2);


        given()
                .basePath("/categorias")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(categoria.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());


    }
}
