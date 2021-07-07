package br.com.ecommerce.seguranca;

import br.com.ecommerce.cadastrousuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Component
public class GeradorToken {


    @Value("${ecommerce.jwt.secret}")
    private String segredo;

    @Value("${ecommerce.jwt.expiration}")
    private long expiracaoEmMilliSegundos;


    public String geraToken(Authentication authentication) {

        UserDetails user = (UserDetails) authentication.getPrincipal();

        final Date now = new Date();

        final Date expiration = new Date(now.getTime() + this.expiracaoEmMilliSegundos);

        return Jwts.builder()
                .setIssuer("Ecommerce API")
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.segredo)
                .compact();

    }
}
