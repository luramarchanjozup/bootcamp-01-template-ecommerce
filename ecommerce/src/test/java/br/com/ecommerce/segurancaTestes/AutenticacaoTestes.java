package br.com.ecommerce.segurancaTestes;

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
public class AutenticacaoTestes {


    @LocalServerPort
    private int port;


    @Test
    public void deveRetornarOKAoAutenticarUsuario() throws JSONException {

        JSONObject usuario = new JSONObject()
                .put("login","teste@email.com")
                .put("senha","teste12345");


        given()
                .basePath("/auth")
                .port(port)
                .header("Content-Type", "application/json")
                .body(usuario.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveRetornarBadRequestAoAutenticarUsuarioNaoCadastrado() throws JSONException {

        JSONObject usuario = new JSONObject()
                .put("login","loginNaoCadastrado@email.com")
                .put("senha","teste12345");


        given()
                .basePath("/auth")
                .port(port)
                .header("Content-Type", "application/json")
                .body(usuario.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

}
