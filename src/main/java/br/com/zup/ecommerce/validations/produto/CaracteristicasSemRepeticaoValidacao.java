package br.com.zup.ecommerce.validations.produto;

import br.com.zup.ecommerce.entities.produto.ProdutoNovoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class CaracteristicasSemRepeticaoValidacao implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoNovoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //1
        if(errors.hasErrors()) {
            return;
        }

        //1
        ProdutoNovoRequest novoProduto = (ProdutoNovoRequest) o;

        Set<String> listaRepetido = novoProduto.caracteristicasIguais();

        //1
        if (!listaRepetido.isEmpty()) {
            errors.rejectValue(
                    "caracteristicas",
                    "Produto.caracteristicas",
                    "contém caracteristica com nome repetido: " + listaRepetido.toString());
        }
    }
}
