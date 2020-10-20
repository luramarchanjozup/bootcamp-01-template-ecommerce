package com.github.marcoscoutozup.ecommerce.validator.transacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = TransacaoValidator.class)
public @interface Transacao {

    String message() default "A transação específica já foi processada como sucesso";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
