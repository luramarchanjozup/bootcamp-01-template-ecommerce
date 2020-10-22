package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.dto.UsuarioDTO;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:4
//1 - Usuario
//1 - UsuarioDTO


@Service
public class UsuarioServices {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(UsuarioDTO usuariodto) {
		Usuario usuario = usuariodto.toModel();
		manager.persist(usuario);
	}

}
