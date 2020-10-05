package com.github.marcoscoutozup.ecommerce.validator.emailunico;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = EmailUnicoValidator.class)
public @interface EmailUnico {

    String message() default "Email do usuário deve ser único";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
