package br.com.zup.ecomerce.nicolle.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ecomerce.nicolle.mensageria.Emails;
import br.com.zup.ecomerce.nicolle.model.Pergunta;
import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.PerguntasRepository;
import br.com.zup.ecomerce.nicolle.repository.ProdutosRepository;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;
import br.com.zup.ecomerce.nicolle.request.PerguntaRequest;
import br.com.zup.ecomerce.nicolle.response.PerguntaResponse;

@RestController
@RequestMapping(value = "/ecomerce/produtos/{id}/perguntas")
public class PerguntaController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private PerguntasRepository perguntasRepository;
	
	@Autowired
	private Emails emails;
	
	@PostMapping
	public ResponseEntity<PerguntaResponse> nova(@RequestBody @Valid PerguntaRequest request, @PathVariable Long id, UriComponentsBuilder builder){
		
		
		
		Produto produto =  produtosRepository.findById(id).get();
		//System.out.println(produto.getId() + "esse é o produto");
		
		Usuario usuario = usuariosRepository.findByLogin("noah@teste.com.br").get();
		
		Pergunta pergunta = request.novaPergunta(usuario, produto);
		
		perguntasRepository.save(pergunta);
		
		URI uri = UriComponentsBuilder.fromPath("/ecomerce/produtos/pergunta/{id}").
				buildAndExpand(pergunta.getId()).toUri();
		
		//o que o adalberto falou pra fazer... interessante.
		emails.novaMensagemPergunta(pergunta);
		
		
		return ResponseEntity.created(uri).body(new PerguntaResponse(pergunta));
	}



}
