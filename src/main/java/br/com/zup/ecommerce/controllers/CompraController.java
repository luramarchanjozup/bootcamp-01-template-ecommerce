package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.compra.CompraNovoRequest;
import br.com.zup.ecommerce.entities.compra.CompraRetorno;
import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.email.EnviarEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * Contagem de carga intrínseca da classe: 6
 */

@RestController
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private EnviarEmail enviarEmail;

    @PostMapping
    //1
    public ResponseEntity<CompraRetorno> realizarCompra(@RequestBody @Valid CompraNovoRequest compraNova, @AuthenticationPrincipal UserDetails user) {

        //1
        Produto produto = manager.find(Produto.class, compraNova.getIdProduto());

        //1
        if (!produto.abateEstoque(compraNova.getQuantidade())) {
            return ResponseEntity.badRequest().build();
        }

        //1
        UsuarioLogado userDetails = (UsuarioLogado) user;
        //1
        Usuario usuario = userDetails.getUsuario();

        enviarEmail.enviarEmail(produto.getDono().getLogin(),"Interesse na compra do produto", "Há um interessado na compra do produto");

        CompraRetorno compraRetorno = new CompraRetorno(
                compraNova.getTipoPagamento(),
                produto.getNome(),
                compraNova.getQuantidade(),
                usuario.getLogin());

        return ResponseEntity.ok(compraRetorno);
    }
}
