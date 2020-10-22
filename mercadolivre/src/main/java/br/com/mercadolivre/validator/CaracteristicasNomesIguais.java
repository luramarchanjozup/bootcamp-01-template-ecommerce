package br.com.mercadolivre.validator;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.mercadolivre.dto.ProdutoDTO;

//Contagem de Pontos - TOTAL:3
//1 - ProdutoDTO
//2 - If


@Component
public class CaracteristicasNomesIguais implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) {
			return ;
		}
		
		ProdutoDTO produtodto = (ProdutoDTO) target;
		Set<String> nomesIguais = produtodto.buscaCaracteristicasIguais();
		
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais "+nomesIguais);
		}
	}

}
