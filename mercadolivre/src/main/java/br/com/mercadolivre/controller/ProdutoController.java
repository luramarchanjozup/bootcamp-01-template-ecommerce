package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.ImagemDTO;
import br.com.mercadolivre.dto.ProdutoDTO;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.seguranca.UsuarioLogado;
import br.com.mercadolivre.service.ProdutoServices;
import br.com.mercadolivre.validator.CaracteristicasNomesIguais;

//Contagem de Pontos - TOTAL:7
//1 - ProdutoServices
//1 - ProdutoDTO
//1 - Produto
//1 - CaracteristicasNomesIguais
//1 - ImagemDTO
//1 - Usuario
//1 - UsuarioLogado

@RestController
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private ProdutoServices produtoServices;
	
	@InitBinder(value = "produtoDTO")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new CaracteristicasNomesIguais());
	}
	

	@PostMapping(value = "/v1/produto")
	@Transactional
	public ResponseEntity<?> criaProduto (@RequestBody @Valid ProdutoDTO produtodto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuarioProduto = usuarioLogado.get();
		Produto produto = produtoServices.salvar(produtodto,usuarioProduto);	
		return new ResponseEntity<>(produto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/v1/imagem/{id}")
	@Transactional
	public ResponseEntity<?> adicionarImagem (@PathVariable("id") Long id, @Valid @RequestBody ImagemDTO imagemdto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario usuarioProduto = usuarioLogado.get();		
		Produto produto = produtoServices.colocarImagem(imagemdto, id, usuarioProduto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
}
