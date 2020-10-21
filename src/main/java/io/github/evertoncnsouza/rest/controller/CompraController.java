package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.enums.GatewayPagamento;
import io.github.evertoncnsouza.domain.enums.StatusCompra;
import io.github.evertoncnsouza.domain.repository.Usuarios;
import io.github.evertoncnsouza.domain.service.Emails;
import io.github.evertoncnsouza.rest.dto.CompraRequest;
import io.github.evertoncnsouza.domain.service.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//7 PCI's
@RestController
@RequestMapping("compras")
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private Emails emails;

    @GetMapping
    @Transactional
    @ResponseStatus(HttpStatus.FOUND)
    public String save(@RequestBody @Valid CompraRequest request,  @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                       UriComponentsBuilder  uriComponentsBuilder) throws BindException {

        Produto produtoASertComprado = manager.find(Produto.class,
                request.getIdProduto());

        if (produtoASertComprado==null){
            BindException problemaComProduto = new BindException(request, "novaCompraRequest");
            problemaComProduto.reject(null, "Produto nao encontrado");
            throw problemaComProduto;
        }

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASertComprado.abataEstoque(quantidade);

        if (abateu) {
            Usuario navegador = usuarioLogado.get();

            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoASertComprado, quantidade, navegador, gateway, StatusCompra.INICIADA);
            manager.persist(novaCompra);
            emails.novaCompra(novaCompra);

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);

        }
            BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
            problemaComEstoque.reject(null, "Não foi possível realizar por conta do estoque");

            throw problemaComEstoque;
        }
    }

