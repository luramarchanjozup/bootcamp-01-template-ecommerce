package br.com.zup.ecomerce.nicolle.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;

@Service
public class AutenticacaoService  implements UserDetailsService{
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario>  usuario = usuariosRepository.findByLogin(username);
		if(usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}
		throw new UsernameNotFoundException("Dados incorretos, verifique e tente novamente.");
	}
}
