package br.com.treino.ecommerce.validations;


import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteValorValidator implements ConstraintValidator<ExisteValor, Object> {

    private String nomeCampo;
    private Class<?> nomeClasse;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteValor constraintAnnotation) {
        nomeCampo = constraintAnnotation.nomeCampo();
        nomeClasse = constraintAnnotation.nomeClasse();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(ObjectUtils.isEmpty(value)){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " +
                nomeClasse.getName() +
                " where " + nomeCampo + "=:value");
        query.setParameter("value", value);

        return !query.getResultList().isEmpty();
    }

}
