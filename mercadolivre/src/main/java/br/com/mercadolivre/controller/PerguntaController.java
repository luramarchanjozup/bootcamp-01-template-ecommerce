package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.PerguntaDTO;
import br.com.mercadolivre.model.Pergunta;
import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.seguranca.UsuarioLogado;
import br.com.mercadolivre.service.EmailServices;


//Contagem de Pontos - TOTAL:5
//1 - PerguntaServices
//1 - PerguntaDTO
//1 - Pergunta
//1 - Usuario
//1 - UsuarioLogado


@RestController
public class PerguntaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	EmailServices emailServices;
	
	@PostMapping(value = "/v1/pergunta")
	@Transactional
	public ResponseEntity<?> adicionaPergunta (@Valid @RequestBody PerguntaDTO perguntadto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuarioProduto = usuarioLogado.get();
		Pergunta pergunta = perguntadto.toModel(manager, usuarioProduto);
		manager.persist(pergunta);
		emailServices.emailNovaPegunta(pergunta);
		return new ResponseEntity<>(pergunta,HttpStatus.OK);
	}
}
