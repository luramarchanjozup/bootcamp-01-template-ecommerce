package com.github.marcoscoutozup.ecommerce.validator.minimodeelementos;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinimoDeElementosValidator implements ConstraintValidator<MinimoDeElementos, List> {

    private int minimoDeElementos;

    @Override
    public void initialize(MinimoDeElementos constraintAnnotation) {
            minimoDeElementos = constraintAnnotation.minimo();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
          return list != null && list.size() >= minimoDeElementos;
    }
}
