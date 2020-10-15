package io.github.evertoncnsouza.validation.constraintvalidation;

import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.UsuarioRepository;
import io.github.evertoncnsouza.rest.dto.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailValidator implements Validator {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            if (errors.hasErrors()) {

            }
        }
        UsuarioRequest request = (UsuarioRequest) target;
        Optional<Usuario> possivelUser = usuarioRepository.findByEmail(request.getEmail());

        if (possivelUser.isPresent()) {
            errors.rejectValue("email", null, "Já existe um(a) outro(a) autor(a) " +
                    "com o email cadastrado " + request.getEmail());
        }
    }
}