package br.com.ecommerce.cadastroprodutoTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.CaracteristicaRequest;
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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CategoriaNaoPodeSerNula {


    private Validator validador;

    private Usuario usuario;

    private List<CaracteristicaRequest> caracteristicas;


    @Before
    public void SetUp(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validador = factory.getValidator();

        usuario = new Usuario("usuariotesteproduto@email.com", new SenhaLimpa("testando"));


        caracteristicas = Arrays.asList(
                new CaracteristicaRequest("teste 1", "120"),
                new CaracteristicaRequest("teste 2", "120"),
                new CaracteristicaRequest("teste 3", "120")
        );

    }


    @Test
    public void CategoriaDoProdutoNaoPodeSerNula(){


        Produto produto = new Produto("Produto Teste", new BigDecimal(30), Long.parseLong("120"),
                caracteristicas, "descricao do produto", null, usuario);


        Set<ConstraintViolation<Produto>> errosDeValidacao = validador.validate(produto);


        Assert.assertTrue(errosDeValidacao.size() >= 1);
        Assert.assertTrue(produto.getNome() != null);
        Assert.assertTrue(produto.getDescricao() != null);
        Assert.assertTrue(produto.getUsuario() != null);
        Assert.assertTrue(produto.getValor() != null);

    }
}
