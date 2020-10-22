package br.com.zup.ecommerce.service.produto;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@Component
public class AtualizacaoProduto {

    @Autowired
    //1
    private EnvioImagem envioImagem;

    @Autowired
    //1
    private EnvioPergunta envioPergunta;

    //1
    public Produto getProduto(Long id, EntityManager manager) {
        Produto produto = manager.find(Produto.class, id);
        //1
        if(produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return produto;
    }

    //1
    public Usuario getUsuarioLogado(UserDetails user) {
        //1
        UsuarioLogado userDetails = (UsuarioLogado) user;
        return userDetails.getUsuario();
    }

    public void validaDonoProduto(EntityManager manager, Produto produto, UserDetails user) {
        Usuario usuario = this.getUsuarioLogado(user);
        //1
        if(!produto.isDonoLogado(manager, usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public Set<String> enviaImagem(ImagensNovoRequest novasImagens) {
        return envioImagem.enviaImagem(novasImagens);
    }

    public void enviaEmail(Produto produto, String pergunta) {
        envioPergunta.enviaEmail(produto, pergunta);
    }
}
