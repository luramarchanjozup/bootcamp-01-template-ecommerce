package br.com.zup.ecomerce.nicolle.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;
import br.com.zup.ecomerce.nicolle.request.UsuarioRequest;
import br.com.zup.ecomerce.nicolle.response.UsuarioResponse;

@RestController
@RequestMapping(value = "/ecomerce/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioResponse> cadastraUsuario(@RequestBody @Valid UsuarioRequest request, UriComponentsBuilder builder) {
		Usuario usuario = request.novoUsuario();
		usuariosRepository.save(usuario);
		
		URI uri = UriComponentsBuilder.fromPath("/ecomerce/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioResponse(usuario));
		
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> teste (@PathVariable Long id) {
		Optional<Usuario> usuarios = usuariosRepository.findById(id);
		System.out.println(usuarios.get().getLogin());
		
		
		return ResponseEntity.ok(usuarios.get().getLogin());
		
		
	}
	

}
