package br.com.zup.ecomerce.nicolle.controller;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ecomerce.nicolle.model.Compra;
import br.com.zup.ecomerce.nicolle.repository.ComprasRepository;
import br.com.zup.ecomerce.nicolle.request.RetornoPagseguroRequest;
import br.com.zup.ecomerce.nicolle.request.RetornoPaypalRequest;
import br.com.zup.ecomerce.nicolle.service.EventoCompraSucesso;
import br.com.zup.ecomerce.nicolle.service.EventosDaNovaCompra;
import br.com.zup.ecomerce.nicolle.service.RetornoGatewayPagamento;

@RestController
@RequestMapping(value = "/ecomerce")
public class FechamentoCompraController {
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	@Autowired
	private EventosDaNovaCompra eventosDaCompra;
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> retornoPagseguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request){
		
		return processaCompra(idCompra, request);
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<?> retornoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request){
		
		return processaCompra(idCompra, request);
	}
	
	
	private ResponseEntity<?> processaCompra(Long idCompra, RetornoGatewayPagamento gateway){
		Compra compra = comprasRepository.findById(idCompra).get();
		compra.tentativaPagamento(gateway);
		
		comprasRepository.save(compra);
		
		eventosDaCompra.processaCompra(compra);
		
		
		return ResponseEntity.ok().build();
	}

}
