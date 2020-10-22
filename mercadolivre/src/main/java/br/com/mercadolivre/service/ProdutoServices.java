package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

@Service
public class ProdutoServices {

	
	@PersistenceContext
	private EntityManager manager;
	
	public Produto salvar(ProdutoDTO produtodto) {
		Query query = manager.createQuery("select u from Usuario u where loguin = :value");
		query.setParameter("value", "gabriel@gmail.com");
		Usuario usuarioProduto = (Usuario) query.getSingleResult();
		
		Produto produto = produtodto.toModel(manager);
		produto.setUsuario(usuarioProduto);
		
		manager.persist(produto);
		return produto;
	}
	
	public Produto colocarImagem (ImagemDTO imagemdto, Long id, Usuario usuario) {
		Produto produto = manager.find(Produto.class, id);
		produto.adicionarImagens(imagemdto.getImagens());
		
		if(produto.getUsuario() != usuario) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		manager.merge(produto);
		return produto;
	}
}
