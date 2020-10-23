package com.zup.mercadolivre.produto.caracteristica;

import com.zup.mercadolivre.produto.NovoProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaDuplicadaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoProdutoRequest request = (NovoProdutoRequest) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if (!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Existem características iguais: " + nomesIguais);
        }
    }
}
