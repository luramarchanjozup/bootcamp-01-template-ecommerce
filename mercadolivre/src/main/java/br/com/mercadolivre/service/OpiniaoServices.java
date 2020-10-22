package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.dto.OpiniaoDTO;
import br.com.mercadolivre.model.Opiniao;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:4
//1 - Opiniao
//1 - OpiniaoDTO
//1 - Produto
//1 - Usuario

@Service
public class OpiniaoServices {

	@PersistenceContext
	private EntityManager manager;
	
	public Opiniao salvar(OpiniaoDTO opiniaodto, Long id) {
		Query query = manager.createQuery("select u from Usuario u where loguin = :value");
		query.setParameter("value", "gabriel@gmail.com");
		Usuario usuarioProduto = (Usuario) query.getSingleResult();
		
		Produto produto = manager.find(Produto.class, id);	
		
		Opiniao opiniao = opiniaodto.toModel(produto, usuarioProduto);
		manager.persist(opiniao);
		
		
		
		return opiniao;
	}
}
