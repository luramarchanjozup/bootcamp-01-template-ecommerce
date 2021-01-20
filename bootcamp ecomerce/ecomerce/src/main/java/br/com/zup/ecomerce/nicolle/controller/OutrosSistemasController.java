package br.com.zup.ecomerce.nicolle.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ecomerce.nicolle.request.CompraNotaFiscalRequest;
import br.com.zup.ecomerce.nicolle.request.CompraRankingRequest;

@RestController
public class OutrosSistemasController {
	
	@PostMapping(value = "/notas-fiscais")
	public void criaNota(@Valid @RequestBody CompraNotaFiscalRequest request) throws InterruptedException{
		System.out.println("criando nota para " + request.getIdCompra() + " do comprador " + request.getIdComprador());
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@Valid @RequestBody CompraRankingRequest request)throws InterruptedException{
		System.out.println("criando ranking " + request.getIdCompra() + " do vendedor " + request.getIdVendedor());
		Thread.sleep(150);
	}
	
	
}
