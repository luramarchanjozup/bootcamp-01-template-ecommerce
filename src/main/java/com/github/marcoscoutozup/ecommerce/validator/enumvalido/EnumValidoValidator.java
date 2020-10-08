package com.github.marcoscoutozup.ecommerce.validator.enumvalido;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class EnumValidoValidator implements ConstraintValidator<EnumValido, String> {

    private Class aClass;

    @Override
    public void initialize(EnumValido constraintAnnotation) {
        aClass = constraintAnnotation.classe();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        return Stream.of(aClass.getFields()).anyMatch(field -> field.getName().equals(valor));
    }
}
