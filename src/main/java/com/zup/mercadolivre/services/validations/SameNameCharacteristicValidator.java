package com.zup.mercadolivre.services.validations;

import java.util.Set;

import com.zup.mercadolivre.controller.form.ProductForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SameNameCharacteristicValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        ProductForm request = (ProductForm)target;

        Set<String> sameNames = request.getSameCharacteristics();
        if(!sameNames.isEmpty()) {
            errors.rejectValue("characteristics", null, "You have some characteristics that are equal " + sameNames);
        }

    }
    
}
