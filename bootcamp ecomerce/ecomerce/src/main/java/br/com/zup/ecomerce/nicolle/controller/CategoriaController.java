package br.com.zup.ecomerce.nicolle.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ecomerce.nicolle.model.Categoria;
import br.com.zup.ecomerce.nicolle.repository.CategoriasRepository;
import br.com.zup.ecomerce.nicolle.request.CategoriaRequest;
import br.com.zup.ecomerce.nicolle.response.CategoriaResponse;

@RestController
@RequestMapping(value = "/ecomerce/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaResponse> cadastraCategoria(@RequestBody @Valid CategoriaRequest request, UriComponentsBuilder builder){
		Categoria categoria = request.novaCategoria(categoriasRepository);
		categoriasRepository.save(categoria);
		
		URI uri = UriComponentsBuilder.fromPath("/ecomerce/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
		
	}

}
