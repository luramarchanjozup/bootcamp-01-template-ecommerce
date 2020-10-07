package com.github.marcoscoutozup.ecommerce.seguranca;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroDeAutorizacaoJwt extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;
    private JwtUtils jwtUtils;

    public FiltroDeAutorizacaoJwt(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(verificarCabecalhoAuthorization(request)){
            String token = request.getHeader("Authorization").substring(7);
            if(jwtUtils.verificarSeTokenEValido(token)){
                String email = jwtUtils.getEmail(token);
                UserDetails user = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private boolean verificarCabecalhoAuthorization(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ");
    }
}
