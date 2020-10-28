package br.com.mercadolivre.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.components.GerenciadorEmail;
import br.com.mercadolivre.model.Pergunta;

//Contagem de Pontos - TOTAL:2
//1 - GerenciadorEmail
//1 - Pergunta

@Service
public class EmailServices {

	
	@Autowired
	GerenciadorEmail gerenciador;
	
	public void emailNovaPegunta(@Valid Pergunta pergunta) {
		gerenciador.enviar
		("<html> Teste Corpo do Email</html>",
		 "Teste Assunto: Nova pergunta",
		 pergunta.getUsuario().getLoguin(),
		 "novapergunta@treinamentomercadolivre.com",
		 pergunta.getProduto().getUsuario().getLoguin()
		);
	}
}
