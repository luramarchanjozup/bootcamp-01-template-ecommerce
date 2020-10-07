package com.github.marcoscoutozup.ecommerce.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtUtils {

    @Value("${jwt.chave}")
    private String chave;

    @Value("${jwt.tempodeexpiracao}")
    private Integer tempoDeExpiracao;

    public String gerarToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(getDataDeExpiracao())
                .signWith(HS512, chave)
                .compact();
    }

    private Date getDataDeExpiracao(){
        return new Date(System.currentTimeMillis() + (tempoDeExpiracao*1000));
    }

    public boolean verificarSeTokenEValido(String token){
        Claims claims = getClaims(token);
        String email = claims.getSubject();
        Date dataDeExpiracao = claims.getExpiration();
        return !email.isEmpty() && new Date().before(dataDeExpiracao);
    }

    private Claims getClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token){
        return getClaims(token).getSubject();
    }

}
