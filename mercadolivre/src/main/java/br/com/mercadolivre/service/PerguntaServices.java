package br.com.mercadolivre.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.dto.PerguntaDTO;
import br.com.mercadolivre.model.Pergunta;
import br.com.mercadolivre.model.Usuario;

//Contagem de Pontos - TOTAL:3
//1 - Pergunta
//1 - PerguntaDTO
//1 - Usuario

@Service
public class PerguntaServices {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	EmailServices emailServices;
	
	public Pergunta cadastrar(PerguntaDTO perguntadto) {
		Query query = manager.createQuery("select u from Usuario u where loguin = :value");
		query.setParameter("value", "gabriel@gmail.com");
		Usuario usuarioProduto = (Usuario) query.getSingleResult();
		
		Pergunta pergunta = perguntadto.toModel(manager, usuarioProduto);
		manager.persist(pergunta);
		
		emailServices.emailNovaPegunta(pergunta);
		
		return pergunta;
	}
}
