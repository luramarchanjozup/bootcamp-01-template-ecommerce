package br.com.mercadolivre.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValidadorIdExistente.class})
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
public @interface IdExistente {

	String message() default "Falha por estar não exister o ID informado";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
	String campo();
	String classe();
	
}
