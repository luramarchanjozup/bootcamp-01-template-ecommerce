package com.github.marcoscoutozup.ecommerce.validator.transacao;

import com.github.marcoscoutozup.ecommerce.compra.enums.StatusPagamento;
import com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento.TentativaDePagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class TransacaoValidator implements ConstraintValidator<Transacao, UUID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(Transacao constraintAnnotation) {

    }

    @Override
    public boolean isValid(UUID transacao, ConstraintValidatorContext constraintValidatorContext) {
        TypedQuery<TentativaDePagamento> response = entityManager.createQuery("SELECT t FROM Compra c JOIN c.transacoes t where t.transacao = :transacao and t.statusPagamento = :status", TentativaDePagamento.class)
                                            .setParameter("transacao", transacao)
                                            .setParameter("status", StatusPagamento.SUCESSO);

        return response.getResultList().isEmpty();
    }
}
