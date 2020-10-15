package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.seguranca.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AutorizacaoUploadImagem {

    @Autowired
    private TokenManager tokenManager;


    public boolean donoDoProduto(HttpServletRequest request, Produto produto){


        String tokenDoUsuarioDaRequisicao = request.getHeader("Authorization");

        String emailDoUsuarioPeloToken = tokenManager.getUserName(tokenDoUsuarioDaRequisicao);

        String emailDoUsuarioPeloProdutoId = produto
                .getUsuario()
                .getLogin();

        return emailDoUsuarioPeloToken
                .equals(emailDoUsuarioPeloProdutoId);

    }
}
