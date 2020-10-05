package com.github.marcoscoutozup.ecommerce.validator.existeid;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, UUID> {

    @PersistenceContext
    private EntityManager entityManager;

    private String classe;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        classe = constraintAnnotation.classe().getSimpleName();
    }

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        if(id == null){
            return true;
        }
        Query query = entityManager.createQuery("select 1 from " + classe + " where id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }
}
