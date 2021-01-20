package br.com.zup.ecomerce.nicolle.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidarValores.class)
public @interface SemValorRepetido {

  String message() default "Valor já cadastrado, verifique a informação e tente novamente!";;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
  String nomeCampo();
  Class<?> dominioClasse();
}
