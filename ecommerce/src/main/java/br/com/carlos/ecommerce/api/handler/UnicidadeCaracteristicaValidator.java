package br.com.carlos.ecommerce.api.handler;

import br.com.carlos.ecommerce.api.dto.RequestProdutoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class UnicidadeCaracteristicaValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RequestProdutoDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        RequestProdutoDto request = (RequestProdutoDto) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Possui caracteristicas iguais "+nomesIguais);
        }
    }
}
