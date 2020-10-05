package com.github.marcoscoutozup.ecommerce.validator.emailunico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return entityManager
                .createNamedQuery("findUsuarioByEmail")
                .setParameter("email", email)
                .getResultList()
                .isEmpty();
    }
}
