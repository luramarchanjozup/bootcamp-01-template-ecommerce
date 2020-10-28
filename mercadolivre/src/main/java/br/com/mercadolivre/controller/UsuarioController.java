package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.UsuarioDTO;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:2
//1 - Usuario
//1 - UsuarioDTO

@RestController
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager manager;

	
	@PostMapping(value = "/v1/usuario")
	@Transactional
	public ResponseEntity<?> criaUsuario (@Valid @RequestBody UsuarioDTO usuariodto) {
		Usuario usuario = usuariodto.toModel();
		manager.persist(usuario);
		
		return new ResponseEntity<>("Usuário criado com sucesso",HttpStatus.OK);
	}
	
}
