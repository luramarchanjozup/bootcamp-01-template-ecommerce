package br.com.ecommerce.cadastroprodutoTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ValorEQuantidadeDevemSerPositivos {


    private Validator validador;

    private Categoria categoria;

    private Usuario usuario;

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

    }

    @Test
    public void ValorDoProdutoNaoPodeSerNuloEDeveSerMaiorQueZero(){


        Produto produtoComValorNulo = new Produto("Produto Teste", null, Long.parseLong("120"),
                caracteristicas, "descricao do produto", categoria, usuario);

        Produto produtoComValorNegativo = new Produto("Produto Teste", new BigDecimal(-20), Long.parseLong("120"),
                caracteristicas, "descricao do produto", categoria, usuario);


        Set<ConstraintViolation<Produto>> errosDeValidacao1 = validador.validate(produtoComValorNulo);

        Set<ConstraintViolation<Produto>> errosDeValidacao2 = validador.validate(produtoComValorNegativo);


        Assert.assertTrue(errosDeValidacao1.size() >= 1);

        Assert.assertTrue(errosDeValidacao2.size() >= 1);

    }

    @Test
    public void QuantidadeDoProdutoNaoPodeSerNuloEDeveSerMaiorQueZero(){

        Produto produtoComQuantidadeNula = new Produto("Produto Teste", new BigDecimal(20), null,
                caracteristicas, "descricao do produto", categoria, usuario);

        Produto produtoComQuantidadeNegativo = new Produto("Produto Teste", new BigDecimal(20), Long.parseLong("-120"),
                caracteristicas, "descricao do produto", categoria, usuario);


        Set<ConstraintViolation<Produto>> errosDeValidacao1 = validador.validate(produtoComQuantidadeNula);

        Set<ConstraintViolation<Produto>> errosDeValidacao2 = validador.validate(produtoComQuantidadeNegativo);


        Assert.assertTrue(errosDeValidacao1.size() >= 1);

        Assert.assertTrue(errosDeValidacao2.size() >= 1);

    }
}
