package com.github.marcoscoutozup.ecommerce.validator.valorunico;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValorUnicoValidator.class)
public @interface ValorUnico {

    String message() default "O valor deve ser único";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String campo();

    Class classe();
}
