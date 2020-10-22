package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mercadolivre.dto.CompraDTO;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.service.CompraServices;


//Contagem de Pontos - TOTAL:4
//1 - CompraServices
//1 - CompraDTO
//1 - Compra
//1 - If

@RestController
public class PagamentoController {

	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CompraServices compraServices;
	
	@PostMapping(value = "/v1/compra")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao (@Valid @RequestBody CompraDTO compradto, UriComponentsBuilder uriComponentsBuilder) {
		Compra compra = compraServices.fecharCompra(compradto);
		if (compra == null) {
			return new ResponseEntity<>("Não foi possível realizar a compra por conta do estoque",HttpStatus.BAD_REQUEST);
		}  
		return new ResponseEntity<>(compra.urlRedirecionamento(uriComponentsBuilder),HttpStatus.FOUND);
	}
}
