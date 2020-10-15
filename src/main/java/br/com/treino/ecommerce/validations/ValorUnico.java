package br.com.treino.ecommerce.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValorUnicoValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

    String message() default "{br.com.treino.ecommerce.validations.valorunico}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

    String nomeCampo();
    Class<?> nomeClasse();

}
