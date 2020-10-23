package br.com.ecommerce.adicionaropiniaoTestes;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NotaDaOpiniaoDeveEstarEntre1e5 {


    private Validator validador;

    private Categoria categoria;

    private Usuario usuario;

    private Produto produto;

    private List<Caracteristica> caracteristicas;



    @Before
    public void SetUp(){


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validador = factory.getValidator();


        categoria = new Categoria("Testando");

        usuario = new Usuario("usuariotesteproduto@email.com", new SenhaLimpa("testando"));


        caracteristicas = Arrays.asList(
                new Caracteristica("teste 1", new BigDecimal(120)),
                new Caracteristica("teste 2", new BigDecimal(120)),
                new Caracteristica("teste 3", new BigDecimal(120))
        );

        produto = new Produto("Produto Teste", new BigDecimal(90), Long.parseLong("120"),
                caracteristicas, "Descrição", categoria, usuario);


    }

    @Test
    public void NotaDeveEstarEntreUmECinco(){


        Opiniao opiniao = new Opiniao(Double.parseDouble("100"), "Opinião teste", "descrição", usuario, produto);

        Set<ConstraintViolation<Opiniao>> errosDeValidacao = validador.validate(opiniao);


        Assert.assertTrue(errosDeValidacao.size() >= 1);

    }
}
