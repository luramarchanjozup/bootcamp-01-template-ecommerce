package br.com.ecommerce.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Component
public class GerenciadorToken {


    @Value("${ecommerce.jwt.secret}")
    private String segredo;


    public Optional<String> buscaTokenNoCabecalhoDaRequisicao(HttpServletRequest request) {

        String authToken = request.getHeader("Authorization");

        return Optional.ofNullable(authToken);

    }


    public boolean tokenEhValido(String jwt) {

        try {

            Jwts
               .parser()
               .setSigningKey(this.segredo)
               .parseClaimsJws(jwt);

            return true;

        } catch (JwtException | IllegalArgumentException e) {

            return false;

        }
    }


    public String buscaNomeDoUsuario(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(this.segredo)
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }

}
