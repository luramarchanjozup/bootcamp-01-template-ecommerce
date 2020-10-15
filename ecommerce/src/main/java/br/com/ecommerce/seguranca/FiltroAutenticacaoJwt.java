package br.com.ecommerce.seguranca;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class FiltroAutenticacaoJwt extends OncePerRequestFilter {


    private GerenciadorToken gerenciadorToken;

    private UsuarioServicos usuarioServicos;

    private AutenticacaoUsuario autenticacaoUsuario;


    public FiltroAutenticacaoJwt(GerenciadorToken gerenciadorToken, UsuarioServicos usuarioServicos,
                                 AutenticacaoUsuario autenticacaoUsuario) {

        this.gerenciadorToken = gerenciadorToken;
        this.usuarioServicos = usuarioServicos;
        this.autenticacaoUsuario = autenticacaoUsuario;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Optional<String> possibleToken = gerenciadorToken.buscaTokenNoCabecalhoDaRequisicao(request);

        if (possibleToken.isPresent() && gerenciadorToken.tokenEhValido(possibleToken.get())) {

            autenticacaoUsuario.autenticaUsuario(possibleToken.get(), gerenciadorToken, usuarioServicos);

        }

        chain.doFilter(request, response);

    }
}
