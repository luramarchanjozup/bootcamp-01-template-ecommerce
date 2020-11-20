package br.com.treino.ecommerce.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenManager {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expirationInMillis;

    public String generateToken(Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication
                .getPrincipal();

        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + this.expirationInMillis);

        return Jwts.builder()
                .setIssuer("Bootcamp - treino ecommerce")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    public String getUserName(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(this.secret)
                .parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
