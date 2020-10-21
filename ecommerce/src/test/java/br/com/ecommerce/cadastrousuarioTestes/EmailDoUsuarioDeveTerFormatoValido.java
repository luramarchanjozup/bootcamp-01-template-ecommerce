package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;


public class EmailDoUsuarioDeveTerFormatoValido {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void usuarioDeveCadastrarLoginEmFormatoValidoDeEmail(){

        Usuario usuarioValidacaoEmail = new Usuario("teste", new SenhaLimpa("teste123"));

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuarioValidacaoEmail);

        assertFalse(violations.isEmpty());

    }

    @Test
    public void loginDoUsuarioNaoPodeSerEmBrancoOuNulo(){

        Usuario usuarioValidacaoEmail = new Usuario("", new SenhaLimpa("teste123"));

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuarioValidacaoEmail);

        assertFalse(violations.isEmpty());

    }
}