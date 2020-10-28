package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.RetornoPagSeguroDTO;
import br.com.mercadolivre.dto.RetornoPaypalDTO;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.service.RetornoPagamentoServices;

//Contagem de Pontos - TOTAL:4
//1 - RetornoPagamentoServices
//1 - RetornoPagSeguroDTO
//1 - Compra
//1 - RetornoPaypalDTO

@RestController
public class RetornoPagamentoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private RetornoPagamentoServices retornopagamentoservices;
	
	@PostMapping(value = "/v1/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid @RequestBody RetornoPagSeguroDTO retornopagsegurodto) {
		Compra compra = retornopagamentoservices.processarPagamento(idCompra, retornopagsegurodto);
		return new ResponseEntity<>(compra,HttpStatus.OK);
	}
	
	@PostMapping(value = "/v1/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<?> processamentoPaypal(@PathVariable("id") Long idCompra, @Valid @RequestBody RetornoPaypalDTO retornopaypaldto) {
		Compra compra = retornopagamentoservices.processarPagamento(idCompra, retornopaypaldto);
		return new ResponseEntity<>(compra,HttpStatus.OK);
	}
	
}
