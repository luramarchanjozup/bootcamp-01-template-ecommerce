package br.com.zup.ecommerce.service.produto;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.uploader.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@Service
public class AtualizacaoProduto {

    @Autowired
    //1
    private Uploader uploader;

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

    //1
    public Set<String> enviaImagem(ImagensNovoRequest novasImagens) {
        return uploader.enviaImagens(novasImagens.getImagens());
    }
}
