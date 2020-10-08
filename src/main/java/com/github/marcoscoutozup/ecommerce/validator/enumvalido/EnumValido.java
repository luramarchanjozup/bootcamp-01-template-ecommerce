package com.github.marcoscoutozup.ecommerce.validator.enumvalido;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = EnumValidoValidator.class)
public @interface EnumValido {

    String message() default "O valor do enum não existe";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class classe();
}
