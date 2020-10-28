package br.com.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mercadolivre.dto.CompraDTO;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;
import br.com.mercadolivre.seguranca.UsuarioLogado;


//Contagem de Pontos - TOTAL:6
//1 - UsuarioLogado
//1 - CompraDTO
//1 - Compra
//1 - Usuario
//1 - Produto
//1 - If

@RestController
public class PagamentoController {

	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/v1/compra")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao (@Valid @RequestBody CompraDTO compradto, UriComponentsBuilder uriComponentsBuilder, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Produto produtoComprado = manager.find(Produto.class, compradto.getIdProduto());
		Assert.notNull(produtoComprado,"O id do produto informado não existe, produto não pode ser Nulo");
		
		boolean abateu = produtoComprado.abataEstoque(compradto.getQuantidade());
		
		if (abateu == true) {
			Usuario usuarioProduto = usuarioLogado.get();
			Compra novaCompra = compradto.toModel(produtoComprado, usuarioProduto);
			manager.persist(novaCompra);		
			return new ResponseEntity<>(novaCompra.urlRedirecionamento(uriComponentsBuilder),HttpStatus.FOUND);
		}	
		
		return new ResponseEntity<>("Não foi possível realizar a compra por conta do estoque",HttpStatus.BAD_REQUEST);
	}
}
