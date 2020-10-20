package com.github.marcoscoutozup.ecommerce.validator.existeid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ExisteIdValidator.class)
public @interface ExisteId {

    String message() default "O objeto não existe";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class classe();
}
