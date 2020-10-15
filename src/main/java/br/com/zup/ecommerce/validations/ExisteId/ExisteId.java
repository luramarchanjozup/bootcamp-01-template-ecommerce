package br.com.zup.ecommerce.validations.ExisteId;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExisteIdValidador.class)
public @interface ExisteId {

    String message() default "não está cadastrado";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String nomeCampo();
    Class<?> dominioClasse();
}
