package com.github.marcoscoutozup.ecommerce.validator.valorunico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private String classe;
    private String campo;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        classe = constraintAnnotation.classe().getSimpleName();
        campo = constraintAnnotation.campo();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + classe + " where " + campo + " = :valor");
        query.setParameter("valor", valor);
        return query.getResultList().isEmpty();
    }
}
