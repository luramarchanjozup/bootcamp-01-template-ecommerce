package br.com.zup.ecomerce.nicolle.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.ecomerce.nicolle.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	//Aqui setei o tempo de vencimento do token que é de 24h, e coloquei o secret, no aplication properties estava dando erro de conversao, não sei pq!
	public static final long expirationTime = 864000000;
	private String secret = "$&vOy%LBaHNjjCVqG1^LCKhKf%i#5NV1qxpdCi0H@dc5WAYzqvP*cNhnUu%oppUyn*tIifniD#mXMdx&B&LtHVry76ooLwOj@Ak3p0!3SvdBz8r6pNJ*VOV$!mLHmVWkCX4QCw^x*EtzsIOA6hFuCBMRiAOMrmbQM6!EQ7j3he8Z3!ZRHs^WqIT9aXb58g0dMhow9Pej";

	public String gerarToken(Authentication authentication) {
		
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + expirationTime);
		
		return Jwts.builder()
				.setIssuer("API Ecommerce ZUP-Nick")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}		
	}

	public Long getIdUsuario(String token) {
		String joana = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getId();
		Long Claro = Long.parseLong(joana);
		System.out.println(Claro);
		
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
