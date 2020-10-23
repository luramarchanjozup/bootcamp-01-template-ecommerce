package br.com.ecommerce.cadastrocategoriaTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class NomeDaCategoriaNaoPodeSerNulo {


    private Validator validator;

    private Categoria categoriaTeste;


    @BeforeEach
    public void setUp() {

        categoriaTeste = new Categoria(" ");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();

    }


    @Test
    public void loginNaoDeveSerEmBrancoNoCadastro(){

        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoriaTeste);

        assertFalse(violations.isEmpty());

    }
}
