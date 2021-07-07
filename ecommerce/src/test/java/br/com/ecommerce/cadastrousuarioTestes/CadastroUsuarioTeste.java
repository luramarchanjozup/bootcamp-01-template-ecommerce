package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import br.com.ecommerce.seguranca.GeradorToken;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroUsuarioTeste {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarOkQuandoCadastrarUsuario(){

        Random instanciaRandom = new Random();

        int aleatorio = instanciaRandom.nextInt(1000000);

        Usuario usuario = new Usuario("teste" + String.format(String.valueOf(aleatorio)) + "@email.com", new SenhaLimpa("teste12345"));

        given()
                .basePath("/usuario")
                    .port(port)
                    .header("Content-Type", "application/json")
                    .body(usuario)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.OK.value());

    }

}
