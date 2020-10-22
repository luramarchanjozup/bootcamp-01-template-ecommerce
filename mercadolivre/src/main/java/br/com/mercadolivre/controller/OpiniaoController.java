package br.com.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.OpiniaoDTO;
import br.com.mercadolivre.model.Opiniao;
import br.com.mercadolivre.service.OpiniaoServices;

//Contagem de Pontos - TOTAL:3
//1 - OpiniaoServices
//1 - OpiniaoDTO
//1 - Opiniao


@RestController
public class OpiniaoController {
	

	@Autowired
	private OpiniaoServices opiniaoServices;

	@PostMapping(value = "/v1/opiniao/{id}")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao (@PathVariable("id") Long id, @Valid @RequestBody OpiniaoDTO opiniaodto) {	
		Opiniao opiniao = opiniaoServices.salvar(opiniaodto, id);
		return new ResponseEntity<>(opiniao,HttpStatus.OK);
	}
	
}
