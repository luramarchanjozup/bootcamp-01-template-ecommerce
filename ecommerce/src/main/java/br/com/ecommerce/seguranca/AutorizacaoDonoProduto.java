package br.com.ecommerce.seguranca;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.seguranca.GerenciadorToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AutorizacaoDonoProduto {

    @Autowired
    private BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;


    public boolean donoDoProduto(HttpServletRequest request, Produto produto){

       String emailDoUsuarioPeloToken = buscaEmailDoUsuarioPeloToken.buscaEmailDoUsuario(request);

        String emailDoUsuarioPeloProdutoId = produto
                .getUsuario()
                .getLogin();

        return emailDoUsuarioPeloToken
                .equals(emailDoUsuarioPeloProdutoId);

    }

}
