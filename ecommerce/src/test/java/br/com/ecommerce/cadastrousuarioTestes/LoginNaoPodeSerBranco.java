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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoginNaoPodeSerBranco {


    private Validator validator;

    private Usuario usuarioValidacaoLogin;


    @BeforeEach
    public void setUp() {

        usuarioValidacaoLogin = new Usuario("", new SenhaLimpa("teste123"));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }


    @Test
    public void loginNaoDeveSerEmBrancoNoCadastro(){

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuarioValidacaoLogin);

        assertFalse(violations.isEmpty());

        assertTrue(!usuarioValidacaoLogin.toString().isBlank());
        assertTrue(!usuarioValidacaoLogin.getSenha().isBlank());

    }
}
