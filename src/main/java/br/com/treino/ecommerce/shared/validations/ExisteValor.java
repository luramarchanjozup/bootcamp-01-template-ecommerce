package br.com.treino.ecommerce.shared.validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExisteValorValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteValor {

    String message() default "{br.com.treino.ecommerce.validations.existevalor}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

    String nomeCampo();
    Class<?> nomeClasse();
}
