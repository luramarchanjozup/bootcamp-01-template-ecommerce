package br.com.mercadolivre.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValidadorValorUnico.class})
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
public @interface ValorUnico {

	String message() default "Falha por estar utilizando um valor já existente no banco de dados";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
	String campo();
	Class<?> classe();
}
