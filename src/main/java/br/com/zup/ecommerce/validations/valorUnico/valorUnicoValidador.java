package br.com.zup.ecommerce.validations.valorUnico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Contagem de carga intrínseca da classe: 2
 */

//1
public class valorUnicoValidador implements ConstraintValidator<ValorUnico, String> {

    private String nomeCampo;
    private Class<?> dominioClasse;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.nomeCampo = constraintAnnotation.nomeCampo();
        this.dominioClasse = constraintAnnotation.dominioClasse();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {

        //1
        if(valor == null) {
            return true;
        }

        List<?> lista = manager
                .createQuery("SELECT 1 FROM "+dominioClasse.getName()+" WHERE "+nomeCampo+" = :valor")
                .setParameter("valor", valor)
                .getResultList();

        return lista.isEmpty();
    }
}
