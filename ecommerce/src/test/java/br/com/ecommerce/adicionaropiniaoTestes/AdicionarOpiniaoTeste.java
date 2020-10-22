package br.com.ecommerce.adicionaropiniaoTestes;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.adicionaropiniao.OpiniaoRequest;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdicionarOpiniaoTeste {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarOkQuandoAdicionarOpiniao(){

        Usuario usuario =
                new Usuario("teste12345@email.com", new SenhaLimpa("teste12345"));

        Produto produto =
                new Produto("Produto teste", new BigDecimal(30.50), Long.parseLong("100"),
                null, "Descrição do produto", null, usuario);

        Opiniao opiniao =
                new Opiniao(Double.parseDouble("4"), "Opinião sobre o produto",
                        "Descrição do produto", usuario, produto);

        given()
                .basePath("/opinioes")
                .port(port)
                .header("Content-Type", "application/json")
                .body(opiniao)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }
}
