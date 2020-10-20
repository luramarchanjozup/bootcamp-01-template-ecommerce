package br.com.zup.ecommerce.entities.produto.opiniao;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.*;

/**
 * Contagem de carga intrínseca da classe: 2
 */

public class OpiniaoProdutoNovoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500, message = "deve ter tamanho igual ou menor que 500")
    private String descricao;

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    //1
    public OpiniaoProduto toModel(Produto produto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //1
        UsuarioLogado userDetails = (UsuarioLogado) authentication.getPrincipal();
        Usuario usuario = userDetails.getUsuario();

        return new OpiniaoProduto(nota,titulo,descricao,usuario, produto);
    }
}
