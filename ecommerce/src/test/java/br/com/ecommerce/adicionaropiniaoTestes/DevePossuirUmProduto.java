package br.com.ecommerce.adicionaropiniaoTestes;

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

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class DevePossuirUmProduto {

    @LocalServerPort
    private int port;

    @Value("${ecommerce.jwt.testes}")
    private String token;


    @Test
    public void opiniaoDevePossuirProdutoId() throws JSONException {


        JSONObject opiniao = new JSONObject()
                .put("nota",3)
                .put("titulo","Teste")
                .put("descricao","descrição teste")
                .put("usuarioId",1)
                .put("produtoId",null);


        given()
                .basePath("/opinioes")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(opiniao.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

}
