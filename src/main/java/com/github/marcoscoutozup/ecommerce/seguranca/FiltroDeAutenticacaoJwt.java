package com.github.marcoscoutozup.ecommerce.seguranca;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcoscoutozup.ecommerce.usuario.UsuarioDTO;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroDeAutenticacaoJwt extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public FiltroDeAutenticacaoJwt(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioDTO usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
           throw new AuthenticationCredentialsNotFoundException("Credenciais não encontradas");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        String token = jwtUtils.gerarToken(username);
        response.addHeader("Authorization", token);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Não autorizado - Credenciais inválidas");
    }
}
