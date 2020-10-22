package br.com.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.CategoriaDTO;
import br.com.mercadolivre.model.Categoria;
import br.com.mercadolivre.service.CategoriaServices;

//Contagem de Pontos - TOTAL:3
//1 - CategoriaServices
//1 - CategoriaDTO
//1 - Categoria

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaServices categoriaServices;
	
	@PostMapping(value = "/v1/categoria")
	@Transactional
	public ResponseEntity<?> criaCategoria (@Valid @RequestBody CategoriaDTO categoriadto) {
		Categoria categoria = categoriaServices.salvar(categoriadto);
		return new ResponseEntity<>(categoria,HttpStatus.OK);
	}
}
