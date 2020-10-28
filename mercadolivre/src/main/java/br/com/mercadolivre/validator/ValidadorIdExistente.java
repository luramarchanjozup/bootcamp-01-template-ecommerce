package br.com.mercadolivre.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Contagem de Pontos - TOTAL:2
//2 - If

public class ValidadorIdExistente implements ConstraintValidator<IdExistente, Long>{

	private String atributo;
	private String classe;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(IdExistente params) {
		atributo = params.campo();
		classe = params.classe();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(classe.equals("Categoria") && value == null) {
			return true;
		}
		
		Query query = manager.createQuery("select 1 from "+classe+" where "+atributo+"=:value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		
		if (list.size() >= 1) {
			return true;
		}
		return false;
	}
}