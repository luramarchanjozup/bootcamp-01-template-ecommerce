package br.com.ecommerce.adicionarespostasTestes;

import br.com.ecommerce.adicionaresposta.Resposta;
import br.com.ecommerce.fazerpergunta.Pergunta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class ValidacoesRespostaModelTestes {

    private Validator validator;

    private Pergunta pergunta;

    private Resposta respostaSemTitulo;


    @BeforeEach
    public void setUp() {

        pergunta = new Pergunta("pergunta teste",null,null);

        respostaSemTitulo = new Resposta("", pergunta);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }


    @Test
    public void categoriaSemNomeNaoDeveSerCadastrada(){

        Set<ConstraintViolation<Resposta>> violations = validator.validate(respostaSemTitulo);

        assertFalse(violations.isEmpty());

    }

}
