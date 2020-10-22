package br.com.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.PerguntaDTO;
import br.com.mercadolivre.model.Pergunta;
import br.com.mercadolivre.service.PerguntaServices;


//Contagem de Pontos - TOTAL:3
//1 - PerguntaServices
//1 - PerguntaDTO
//1 - Pergunta


@RestController
public class PerguntaController {
	
	@Autowired
	PerguntaServices perguntaServices;
	
	@PostMapping(value = "/v1/pergunta")
	@Transactional
	public ResponseEntity<?> adicionaPergunta (@Valid @RequestBody PerguntaDTO perguntadto) {
		Pergunta pergunda = perguntaServices.cadastrar(perguntadto);
		return new ResponseEntity<>(pergunda,HttpStatus.OK);
	}
}
