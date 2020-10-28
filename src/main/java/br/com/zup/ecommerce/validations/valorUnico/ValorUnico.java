package br.com.zup.ecommerce.validations.valorUnico;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = valorUnicoValidador.class)
public @interface ValorUnico {

    String message() default "já cadastrado";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String nomeCampo();
    Class<?> dominioClasse();
}
