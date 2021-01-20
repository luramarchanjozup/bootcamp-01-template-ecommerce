package br.com.zup.ecomerce.nicolle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.repository.ProdutosRepository;
import br.com.zup.ecomerce.nicolle.response.DetalhaProdutoResponse;

@RestController
@RequestMapping(value = "/produtos/{id}")
public class PaginaDetalheProdutoController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public DetalhaProdutoResponse detalhaProdutoResponse (@PathVariable("id") Long id){
		Produto produto =  produtosRepository.findById(id).get();
		return new DetalhaProdutoResponse(produto);
		
	}

}
