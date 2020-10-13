package io.github.evertoncnsouza.validation.constraintvalidation;

import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.domain.repository.Users;
import io.github.evertoncnsouza.rest.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailValidator implements Validator {

    @Autowired
    private Users users;


    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            if (errors.hasErrors()) {

            }
        }
        UserRequest request = (UserRequest) target;
        Optional<User> possivelUser = users.findByEmail(request.getEmail());

        if (possivelUser.isPresent()) {
            errors.rejectValue("email", null, "Já existe um(a) outro(a) autor(a) " +
                    "com o email cadastrado " + request.getEmail());
        }
    }
}