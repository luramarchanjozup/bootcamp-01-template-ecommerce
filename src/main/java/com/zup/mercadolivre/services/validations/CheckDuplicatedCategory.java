package com.zup.mercadolivre.services.validations;

import java.util.Optional;

import com.zup.mercadolivre.controller.form.CategoryForm;
import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckDuplicatedCategory implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        CategoryForm request = (CategoryForm)target;
        Optional<Category> possibleCategory = categoryRepository.findByName(request.getName());

        if (possibleCategory.isPresent()){
            errors.rejectValue("name", null, "Category '" + request.getName() + "' already exists.");
        }
    }
    
}
