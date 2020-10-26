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

import br.com.mercadolivre.dto.CategoriaDTO;
import br.com.mercadolivre.model.Categoria;

//Contagem de Pontos - TOTAL:3
//1 - CategoriaDTO
//1 - Categoria

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/v1/categoria")
	@Transactional
	public ResponseEntity<?> criaCategoria (@Valid @RequestBody CategoriaDTO categoriadto) {
		Categoria categoria = categoriadto.toModel(manager);
		manager.persist(categoria);
		return new ResponseEntity<>(categoria,HttpStatus.OK);
	}
}
