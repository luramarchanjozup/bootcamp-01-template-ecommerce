package br.com.zup.ecommerce.service.produto;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.email.EnviarEmail;
import br.com.zup.ecommerce.service.uploader.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@Component
public class AtualizacaoProduto {

    @Autowired
    //1
    private Uploader uploader;

    @Autowired
    //1
    private EnviarEmail enviarEmail;

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

    public Set<String> enviaImagem(List<MultipartFile> novasImagens) {
        return uploader.enviaImagens(novasImagens);
    }

    public void enviaEmailPergunta(Produto produto, String pergunta) {
        String assunto = this.montarAssuntoEmailPergunta(produto);
        String mensagem = this.montarCorpoEmailPergunta(produto, pergunta);
        enviarEmail.enviarEmail(produto.getDono().getLogin(), assunto, mensagem);
    }

    private String montarAssuntoEmailPergunta(Produto produto) {
        return String.format(
                "Pergunta sobre o produto %d - %s",
                produto.getId(), produto.getNome());
    }

    private String montarCorpoEmailPergunta(Produto produto, String pergunta){

        String host = ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString();
        String linkProduto = String.format("%s/produtos/%d", host, produto.getId());

        return String.format(
                "Pergunta sobre o produto %d - %s\n\n" +
                        "Pergunta: %s\n\n" +
                        "Link do produto: %s",
                produto.getId(), produto.getNome(),
                pergunta,
                linkProduto);
    }
}
