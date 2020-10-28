package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import br.com.mercadolivre.dto.ImagemDTO;
import br.com.mercadolivre.dto.ProdutoDTO;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:4
//1 - ImagemDTO
//1 - ProdutoDTO
//1 - Produto
//1 - Usuario
//1 - If

@Service
public class ProdutoServices {

	
	@PersistenceContext
	private EntityManager manager;
	
	public Produto salvar(ProdutoDTO produtodto, Usuario usuarioProduto) {	
		Produto produto = produtodto.toModel(manager);
		produto.setUsuario(usuarioProduto);
		
		manager.persist(produto);
		return produto;
	}
	
	public Produto colocarImagem (ImagemDTO imagemdto, Long id, Usuario usuario) {
		Produto produto = manager.find(Produto.class, id);
		Assert.notNull(produto,"O id do produto informado não existe, produto não pode ser Nulo");
		
		produto.adicionarImagens(imagemdto.getImagens());
		
		if(!produto.getUsuario().getLoguin().equals(usuario.getLoguin())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		manager.merge(produto);
		return produto;
	}
}
