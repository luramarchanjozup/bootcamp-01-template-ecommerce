package br.com.zup.ecomerce.nicolle.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.CategoriasRepository;
import br.com.zup.ecomerce.nicolle.repository.ProdutosRepository;
import br.com.zup.ecomerce.nicolle.repository.RepositorioFake;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;
import br.com.zup.ecomerce.nicolle.request.ImagensRequest;
import br.com.zup.ecomerce.nicolle.request.ProdutoRequest;
import br.com.zup.ecomerce.nicolle.response.ProdutosResponse;
import br.com.zup.ecomerce.nicolle.validator.SemCaracteristicasIguais;

@RestController
@RequestMapping(value = "/ecomerce/produtos")
public class ProdutosController {
	//1
	@Autowired
	private CategoriasRepository categoriasRepository;
	//2
	@Autowired
	private UsuariosRepository usuariosRepository;
	//3
	@Autowired
	private ProdutosRepository produtosRepository;
	//4
	@Autowired
	private RepositorioFake repositorioFake;
	//5
	@InitBinder(value =  "produtoRequest")
	public void init(WebDataBinder binder) {
		binder.addValidators(new SemCaracteristicasIguais());
	}
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutosResponse> cadastraProduto(@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder builder){
		
		//esse vendedor é set de teste
		Usuario vendedor = usuariosRepository.findByLogin("noah@teste.com.br").get();
		System.out.println(request.toString());
		Produto produto = request.novoProduto(categoriasRepository, vendedor);
		produtosRepository.save(produto);
		
		URI uri = UriComponentsBuilder.fromPath("/ecomerce/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProdutosResponse(produto));
		
	}
	
	
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagem(@PathVariable("id") Long idProduto, @Valid ImagensRequest request){
		
		//esse vendedor é set de teste
		Usuario vendedor = usuariosRepository.findByLogin("noah@teste.com.br").get();
		Produto produto = manager.find(Produto.class, idProduto);
		//como fazer isso com o repository????
		//6
		if(!produto.pertenceAoUsuario(vendedor)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	
		}
		
		Set<String> links = repositorioFake.send(request.getImagens());
		System.out.println(links);
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return ResponseEntity.ok(produto.toString());
		
	}
	
	//porque fazer separado?// entendido pelo vídeo, sobre a carga cognitiva e pontuação!
	//quando meu usuario faz cadastro de produto o upload da imagem não é junto? 
	//ou no caso o front faz o cadastro puxando os dois point-end?
	
	// 2 vídeos diferentes da algaworks + 3 videos de indianos, achei fazendo em JS, mas nada a ver
	//como fazer isso com repository??? SOCORRO, trem chato! saoisjaoisjao
//	@Autowired 
//	private Disco disco;
//	
//	@PostMapping(value = "/{id}/imagens")
//	public void upload(@RequestParam MultipartFile foto) {
//		disco.save(foto);
//		
//	}
	
	
	


}
