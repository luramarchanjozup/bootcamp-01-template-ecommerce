package br.com.ecommerce.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BuscaEmailDoUsuarioPeloToken {

    @Autowired
    private GerenciadorToken gerenciadorToken;


    public String buscaEmailDoUsuario(HttpServletRequest request){

        String tokenDoUsuarioDaRequisicao = request.getHeader("Authorization");

        String emailDoUsuarioPeloToken = gerenciadorToken.buscaNomeDoUsuario(tokenDoUsuarioDaRequisicao);

        return emailDoUsuarioPeloToken;

    }


}
