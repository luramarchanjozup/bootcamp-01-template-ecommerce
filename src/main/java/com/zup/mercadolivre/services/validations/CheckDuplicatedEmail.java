package com.zup.mercadolivre.services.validations;

import java.util.Optional;

import com.zup.mercadolivre.controller.form.UserForm;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckDuplicatedEmail implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        UserForm request = (UserForm) target;
        Optional<User> possibleUser = userRepository.findByEmail(request.getEmail());

        if (possibleUser.isPresent()) {
            errors.rejectValue("email", null, "User with email " + request.getEmail() + " already exists");
        }
    }
    
}
