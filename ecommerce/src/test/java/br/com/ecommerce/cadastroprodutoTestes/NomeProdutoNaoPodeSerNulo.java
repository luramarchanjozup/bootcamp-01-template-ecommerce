package br.com.ecommerce.cadastroprodutoTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.CaracteristicaRequest;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class NomeProdutoNaoPodeSerNulo {


    @Test
    public void nomeDoProdutoDeveExistir(){


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Validator validador = factory.getValidator();


        Categoria categoria = new Categoria("Testando");


        Usuario usuario = new Usuario("usuariotesteproduto@email.com", new SenhaLimpa("testando"));


        List<CaracteristicaRequest> caracteristicas = Arrays.asList(
                new CaracteristicaRequest("teste 1", "120"),
                new CaracteristicaRequest("teste 2", "120"),
                new CaracteristicaRequest("teste 3", "120")
        );


        Produto produto = new Produto(" ", new BigDecimal(120), Long.parseLong("120"),
                caracteristicas, "descricao do produto", categoria, usuario);


        Set<ConstraintViolation<Produto>> errosDeValidacao = validador.validate(produto);

        Assert.assertTrue(errosDeValidacao.size() >= 1);

    }
}
