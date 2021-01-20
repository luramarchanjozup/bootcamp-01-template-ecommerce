package br.com.zup.ecomerce.nicolle.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ecomerce.nicolle.model.Opiniao;
import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.OpiniaoRepository;
import br.com.zup.ecomerce.nicolle.repository.ProdutosRepository;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;
import br.com.zup.ecomerce.nicolle.request.OpiniaoRequest;

@RestController
@RequestMapping(value = "/ecomerce/produtos/{id}/opiniao")
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@Autowired
	private ProdutosRepository rep;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> adiciona(@RequestBody @Valid OpiniaoRequest request, @PathVariable Long id){
		
		//COMO FAZER ISSO COM O REPOSITORY? 
		Produto produto = manager.find(Produto.class, id);
		
		//Assim?
		//Produto prod =  rep.findById(id).get();
		
		Usuario consumidor = usuariosRepository.findByLogin("noah@teste.com.br").get();
		
		Opiniao opiniao = request.novaOpiniao(produto, consumidor);
		opiniaoRepository.save(opiniao);
		
		return ResponseEntity.ok().build();
		
	}

}
