package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.retorno.ProdutoRetornoDTO;
import br.com.mercadolivre.model.Produto;

//Contagem de Pontos - TOTAL:2
//1 - Produto
//1 - ProdutoRetornoDTO


@RestController
public class DetalheController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/v1/listarproduto/{id}")
	public ResponseEntity<?> detalheLivro(@PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		ProdutoRetornoDTO produtoRetorno = new ProdutoRetornoDTO(produto);
		return new ResponseEntity<>(produtoRetorno, HttpStatus.OK);
	}

}
