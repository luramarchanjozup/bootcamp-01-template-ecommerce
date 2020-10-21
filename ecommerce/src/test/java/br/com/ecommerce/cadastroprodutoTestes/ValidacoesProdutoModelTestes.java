package br.com.ecommerce.cadastroprodutoTestes;

import br.com.ecommerce.cadastroproduto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class ValidacoesProdutoModelTestes {


    private Validator validator;

    private Produto produtoSemNome, produtoSemDescricao;

    @BeforeEach
    public void setUp() {
        produtoSemNome = new Produto("", BigDecimal.valueOf(30.50), Long.parseLong("100"),
                null, "descrição teste", null, null);

        produtoSemDescricao = new Produto("titulo teste", BigDecimal.valueOf(30.50), Long.parseLong("100"),
                null, "", null, null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void produtoSemDescricaoNaoDeveSerCadastrado(){

        Set<ConstraintViolation<Produto>> violations = validator.validate(produtoSemDescricao);

        assertFalse(violations.isEmpty());

    }


    @Test
    public void produtoSemNomeNaoDeveSerCadastrado(){

        Set<ConstraintViolation<Produto>> violations = validator.validate(produtoSemNome);

        assertFalse(violations.isEmpty());

    }

}
