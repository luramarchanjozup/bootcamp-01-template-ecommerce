package br.com.ecommerce.adicionaropiniaoTestes;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class DescricaoEhObrigatoriaEPossuiMaximoCaracteres {


    @Value("${ecommerce.jwt.testes}")
    private String token;


    @LocalServerPort
    private int port;


    @Test
    public void descricaoEhObrigatoria() throws JSONException {


        JSONObject opiniao = new JSONObject()
                .put("nota",3)
                .put("titulo","teste")
                .put("descricao","  ")
                .put("usuarioId",1)
                .put("produtoId",1);


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

    @Test
    public void descricaoPossuiMaximoDeCaracteres() throws JSONException {


        String maisDeMilCaracateres = StringUtils.repeat("*", 1001);


        JSONObject opiniao = new JSONObject()
                .put("nota",3)
                .put("titulo","teste")
                .put("descricao",maisDeMilCaracateres)
                .put("usuarioId",1)
                .put("produtoId",1);



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
