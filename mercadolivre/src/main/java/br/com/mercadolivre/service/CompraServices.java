package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.dto.CompraDTO;
import br.com.mercadolivre.model.Compra;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:5
//1 - Compra
//1 - CompraDTO
//1 - Produto
//1 - If
//1 - Usuario


@Service
public class CompraServices {

	@PersistenceContext
	private EntityManager manager;
	
	public Compra fecharCompra(CompraDTO compradto) {
		Produto produtoComprado = manager.find(Produto.class, compradto.getIdProduto());
		boolean abateu = produtoComprado.abataEstoque(compradto.getQuantidade());
		
		if (abateu == true) {
			Query query = manager.createQuery("select u from Usuario u where loguin = :value");
			query.setParameter("value", "gabriel@gmail.com");
			Usuario usuarioProduto = (Usuario) query.getSingleResult();

			Compra novaCompra = compradto.toModel(produtoComprado, usuarioProduto);
			manager.persist(novaCompra);		
			return novaCompra;
		}
		return null;
	}
}
