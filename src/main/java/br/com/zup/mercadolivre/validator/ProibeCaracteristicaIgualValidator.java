package br.com.zup.mercadolivre.validator;

import br.com.zup.mercadolivre.dto.request.ProdutoRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ProdutoRequestDTO request = (ProdutoRequestDTO) target;

        Set<String> nomesIguais = request.caracteristicasIguais();
        if(!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "já cadastrada");
        }
    }
}
