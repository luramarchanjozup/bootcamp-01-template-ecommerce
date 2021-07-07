package br.com.ecommerce.cadastroprodutoTestes;


import br.com.ecommerce.cadastroproduto.CadastroProdutoRequest;
import br.com.ecommerce.cadastroproduto.CaracteristicaRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class CadastrarProdutoDeveRetornarOk {


    @LocalServerPort
    private int port;


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @Test
    public void deveRetornaroKQuandoCadastrarProduto() throws JSONException {


        JSONObject caracteristica = new JSONObject()
                .put("nome", "teste")
                .put("valor", "teste");


        JSONArray caracteristicas = new JSONArray()
                .put(caracteristica)
                .put(caracteristica)
                .put(caracteristica);


        JSONObject produto = new JSONObject()
                .put("nome","teste")
                .put("valor",123.0)
                .put("quantidadeDisponivel",100)
                .put("descricao","teste")
                .put("caracteristicasCadastro", caracteristicas)
                .put("categoriaId",1)
                .put("usuarioId",1);


        given()
                .basePath("/produtos")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(produto.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());

    }


    @Test
    public void deveRetornarBadRequestQuandoProdutoEhNulo() throws JSONException {

        List<CaracteristicaRequest> caracteristicaRequests = new ArrayList<>();

        CaracteristicaRequest caracteristica1 = new CaracteristicaRequest("teste1", "teste");
        CaracteristicaRequest caracteristica2 = new CaracteristicaRequest("teste1", "teste");
        CaracteristicaRequest caracteristica3 = new CaracteristicaRequest("teste1", "teste");

        caracteristicaRequests.add(caracteristica1);
        caracteristicaRequests.add(caracteristica2);
        caracteristicaRequests.add(caracteristica3);

        JSONObject produto = new JSONObject()
                .put("nome","  ")
                .put("valor",123.0)
                .put("quantidadeDisponivel",100)
                .put("descricao","teste")
                .put("caracteristicasCadastro", caracteristicaRequests)
                .put("categoriaId",1)
                .put("usuarioId",1);


        given()
                .basePath("/produtos")
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(produto.toString())
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());


    }
}
