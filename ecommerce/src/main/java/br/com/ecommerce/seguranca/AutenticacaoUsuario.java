package br.com.ecommerce.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoUsuario {

    public void autenticaUsuario(String token, GerenciadorToken gerenciadorToken, UsuarioServicos usuarioServicos){


        String userName = gerenciadorToken.buscaNomeDoUsuario(token);

        UserDetails userDetails = usuarioServicos.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

    }

}
