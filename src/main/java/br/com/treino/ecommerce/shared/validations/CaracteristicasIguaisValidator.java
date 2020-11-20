package br.com.treino.ecommerce.shared.validations;

import br.com.treino.ecommerce.request.NovaCaracteristicaRequest;
import br.com.treino.ecommerce.request.NovoProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

@Component
public class CaracteristicasIguaisValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        return NovoProdutoRequest.class.isAssignableFrom(clazz); //1
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()){ //2
            return;
        }

        NovoProdutoRequest novoProdutoRequest = (NovoProdutoRequest) target;

        Set<String> nomeIgual = new HashSet<>();
        Set<String> nomeDiferente = new HashSet<>();

        for (NovaCaracteristicaRequest caracteristica: //3
                novoProdutoRequest.getCaracteristicas()){  //4
            if(!nomeDiferente.add(caracteristica.getTitulo())){ //5
                nomeIgual.add(caracteristica.getTitulo());
            }
        }

        if(!nomeIgual.isEmpty()){ //6
            errors.rejectValue("caracteristicas",null, "Existe uma " +
                    "característica com o mesmo nome: " + nomeIgual);
        }

    }

}
