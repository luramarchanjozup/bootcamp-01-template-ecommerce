package io.github.evertoncnsouza.validation.constraintvalidation;

import io.github.evertoncnsouza.rest.dto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

    ProdutoRequest request = (ProdutoRequest) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Olha, você tem " +
                    "caracteristicas iguais "+nomesIguais );
        }

    }
}
