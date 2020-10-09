package br.com.ecommerce.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private UsersServices usersService;

    public JwtAuthenticationFilter(TokenManager tokenManager, UsersServices usersService) {
        this.tokenManager = tokenManager;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Optional<String> possibleToken = getTokenFromRequest(request);

        if (tokenManager.isValid(possibleToken.get()))
        {
            authenticateUser(possibleToken.get());
        }


        chain.doFilter(request, response);

    }


    public void authenticateUser(String token){


        String userName = tokenManager.getUserName(token);

        UserDetails userDetails = usersService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

    }


    private Optional<String> getTokenFromRequest(HttpServletRequest request) {

        String authToken = request.getHeader("Authorization");

        return Optional.ofNullable(authToken);

    }
}
