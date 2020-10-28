package br.com.zup.ecommerce.validations.compra;

import br.com.zup.ecommerce.entities.compra.CompraNovoRequest;
import br.com.zup.ecommerce.entities.produto.Produto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Contagem de carga intrínseca da classe: 4
 */

@Component
public class EstoqueDisponivelValidador implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return CompraNovoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //1
        if(errors.hasErrors()) {
            return;
        }

        //1
        CompraNovoRequest compra = (CompraNovoRequest) o;

        //1
        Produto produto = manager.find(Produto.class, compra.getIdProduto());

        //1
        if(produto.getQtdDisponivel() - compra.getQuantidade() < 0) {
            errors.rejectValue(
                    "quantidade",
                    "Compra.quantidade",
                    "quantidade indisponível no estoque");
        }
    }
}
