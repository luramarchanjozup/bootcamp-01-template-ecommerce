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

public class ValidacoesCategoriaModelTestes {

    private Validator validator;

    private Categoria categoriaSemNome;

    private Categoria categoriaMae;


    @BeforeEach
    public void setUp() {

        categoriaMae = new Categoria("categoria Mãe", null);

        categoriaSemNome = new Categoria("", categoriaMae);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }


    @Test
    public void categoriaSemNomeNaoDeveSerCadastrada(){

        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoriaSemNome);

        assertFalse(violations.isEmpty());

    }

}
