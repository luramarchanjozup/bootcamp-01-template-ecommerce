package com.zup.mercadolivre.compra;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;
import com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Transactional
    @ResponseStatus(HttpStatus.FOUND)
    public String efetuarCompra(@Valid @RequestBody NovaCompraRequest request, Authentication authentication,
                                UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produtoEscolhido = manager.find(Produto.class, request.getIdProduto());
        int quantidade = request.getQuantidade();
        boolean abateu = produtoEscolhido.abateEstoque(quantidade);
        if (abateu) {
            Usuario comprador = usuarioRepository.findByEmail(authentication.getName());
            GatewayPagamento gateway = request.getGateway();
            Compra novaCompra = new Compra(produtoEscolhido, quantidade, comprador, gateway);
            manager.persist(novaCompra);
            return novaCompra.urlRedirecionamento(uriComponentsBuilder);
        }
        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");
        throw problemaComEstoque;
    }
}
