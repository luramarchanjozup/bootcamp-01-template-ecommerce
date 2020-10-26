package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.OpiniaoDTO;
import br.com.mercadolivre.model.Opiniao;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.seguranca.UsuarioLogado;

//Contagem de Pontos - TOTAL:4
//1 - Produto
//1 - OpiniaoDTO
//1 - Opiniao
//1 - Usuario


@RestController
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/v1/opiniao/{id}")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao (@PathVariable("id") Long id, @Valid @RequestBody OpiniaoDTO opiniaodto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuarioProduto = usuarioLogado.get();
		System.out.println(usuarioProduto.toString());
		Produto produto = manager.find(Produto.class, id);	
		
		Opiniao opiniao = opiniaodto.toModel(produto, usuarioProduto);
		manager.persist(opiniao);
		
		
		return new ResponseEntity<>(opiniao,HttpStatus.OK);
	}
	
}
