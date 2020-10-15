package br.com.treino.ecommerce.validations;


import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.request.NovoUsuarioRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class EmailDuplicadoValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NovoUsuarioRequest request = (NovoUsuarioRequest) target;

        Query query = entityManager
                .createQuery("select login from " + Usuario.class.getName()+
                        " where login =:value");
        query.setParameter("value", request.getLogin());

        if(!query.getResultList().isEmpty()){
            errors.rejectValue("login", null,
                    "Email já cadastrado");
        }

    }
}
