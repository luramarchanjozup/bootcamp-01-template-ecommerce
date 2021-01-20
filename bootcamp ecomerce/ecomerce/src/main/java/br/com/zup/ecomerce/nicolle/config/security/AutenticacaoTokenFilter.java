package br.com.zup.ecomerce.nicolle.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UsuariosRepository usuariosRepository;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuariosRepository usuariosRepository) {
		this.tokenService = tokenService;
		this.usuariosRepository = usuariosRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperaToken(request);
		//System.out.println(token);
		boolean valido = tokenService.isTokenValido(token);
		//System.out.println(valido);
		if (valido) {
			autenticaCliente(token);	
		}
		filterChain.doFilter(request, response);
	}

	private void autenticaCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuariosRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Baerer ")) {
			return null;
		}
		return token.substring(7, token.length());	
	}
}
