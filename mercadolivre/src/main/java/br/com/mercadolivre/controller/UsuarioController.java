package br.com.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.UsuarioDTO;
import br.com.mercadolivre.service.UsuarioServices;

//Contagem de Pontos - TOTAL:2
//1 - UsuarioServices
//1 - UsuarioDTO

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioServices usuarioServices;

	
	@PostMapping(value = "/v1/usuario")
	@Transactional
	public ResponseEntity<?> criaUsuario (@Valid @RequestBody UsuarioDTO usuariodto) {
		usuarioServices.salvar(usuariodto);
		return new ResponseEntity<>("Usuário criado com sucesso",HttpStatus.OK);
	}
	
}
