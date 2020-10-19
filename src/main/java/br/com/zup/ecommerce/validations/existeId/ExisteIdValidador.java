package br.com.zup.ecommerce.validations.existeId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Contagem de carga intrínseca da classe: 2
 */

//1
public class ExisteIdValidador implements ConstraintValidator<ExisteId, Long> {

    @PersistenceContext
    private EntityManager manager;

    private String nomeCampo;
    private Class<?> dominioClasse;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        nomeCampo = constraintAnnotation.nomeCampo();
        dominioClasse = constraintAnnotation.dominioClasse();
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {

        //1
        if (aLong == null) {
            return true;
        }

        List<?> lista =  manager
                .createQuery("SELECT 1 FROM "+dominioClasse.getName()+" WHERE "+nomeCampo+" = :valor")
                .setParameter("valor", aLong)
                .getResultList();

        return !lista.isEmpty();
    }
}
