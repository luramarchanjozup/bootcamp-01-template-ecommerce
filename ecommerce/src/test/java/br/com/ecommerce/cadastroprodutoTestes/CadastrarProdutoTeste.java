package br.com.ecommerce.cadastroprodutoTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.CadastroProdutoRequest;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.Produto;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastrarProdutoTeste {


    @LocalServerPort
    private int port;


    @Test
    public void deveRetornarOkQuandoCadastrarProduto(){


        Caracteristica caracteristica1 = new Caracteristica("teste1", new BigDecimal(30.9));

        Caracteristica caracteristica2 = new Caracteristica("teste2", new BigDecimal(30.0));

        Caracteristica caracteristica3 = new Caracteristica("teste3", new BigDecimal(30.0));


        List<Caracteristica> caracteristicas = Arrays
                .asList(new Caracteristica[]{caracteristica1, caracteristica2, caracteristica3});


        CadastroProdutoRequest produto = new CadastroProdutoRequest("Nome do produto", BigDecimal.valueOf(40.5), Long.parseLong("150"),
                caracteristicas, "descrição do produto", Long.parseLong("1"),  Long.parseLong("1"));


        /* ainda estou tentando autorizar acesso aos endpoints aqui com o Rest Assured */

        String token = "";

        given()
                .basePath("/produtos")
                .port(port)
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .body(produto)
                    .when()
                .post()
                    .then()
                .statusCode(HttpStatus.OK.value());


    }
}
