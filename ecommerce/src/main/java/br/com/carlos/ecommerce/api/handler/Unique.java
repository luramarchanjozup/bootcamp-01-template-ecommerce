package br.com.carlos.ecommerce.api.handler;

import javax.validation.Payload;

public @interface Unique {
    String message() default "duplicado";
    Class<? extends Payload>[] payload() default { };
    Class<?>[] groups() default { };

    String fieldName();
    Class<?> domainClass();
}
