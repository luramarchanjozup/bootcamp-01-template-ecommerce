package br.com.ecommerce.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValidacao implements ConstraintValidator<Unico, Object> {


    @PersistenceContext
    private EntityManager entityManager;

    private String atributo;

    private Class<?> classe;


    @Override
    public void initialize(Unico constraintAnnotation) {

        atributo = constraintAnnotation.fieldName();

        classe = constraintAnnotation.domainClass();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        return entityManager
                   .createQuery("select 1 from " + classe.getName() + " where "+ atributo + "=:value")
                   .setParameter("value", o)
                   .getResultList()
                   .isEmpty();

    }
}
