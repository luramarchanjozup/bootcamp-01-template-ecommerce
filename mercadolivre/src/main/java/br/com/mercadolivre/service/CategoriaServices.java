package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.dto.CategoriaDTO;
import br.com.mercadolivre.model.Categoria;

//Contagem de Pontos - TOTAL:2
//1 - Categoria
//1 - CategoriaDTO


@Service
public class CategoriaServices {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Categoria salvar(CategoriaDTO cateogoriadto) {
		Categoria categoria = cateogoriadto.toModel(manager);
		manager.persist(categoria);
		return categoria;
	}

}
