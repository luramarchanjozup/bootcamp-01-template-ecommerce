package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FalhaDeValidacaoCadastroUsuario {


    @LocalServerPort
    private int port;


    @Test
    public void deveRetornarNotFoundQuandoTiverFalhaDeValidacaoAoCadastrarUsuario(){


        int aleatorio = new Random().nextInt(1000000);


        String emailAleatorio = "teste" + String.format(String.valueOf(aleatorio)) + "@email.com";


        Usuario usuario1 = new Usuario(emailAleatorio, new SenhaLimpa("teste12345"));

        given()
                .basePath("/usuario")
                .port(port)
                .header("Content-Type", "application/json")
                .body(usuario1)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());



        Usuario usuario2 = new Usuario(emailAleatorio, new SenhaLimpa("teste12345"));


        given()
                .basePath("/usuario")
                .port(port)
                .header("Content-Type", "application/json")
                .body(usuario2)
                    .when()
                .post()
                    .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}
