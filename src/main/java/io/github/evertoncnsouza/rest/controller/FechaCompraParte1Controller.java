package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.Usuarios;
import io.github.evertoncnsouza.domain.service.Emails;
import io.github.evertoncnsouza.rest.dto.CompraRequest;
import io.github.evertoncnsouza.domain.enums.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("compras")
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private Emails emails;


    @PostMapping
    @Transactional
    public String save(@RequestBody @Valid CompraRequest request, UriComponentsBuilder
            uriComponentsBuilder) throws BindException {

        Produto produtoASertComprado = manager.find(Produto.class,
                request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASertComprado.abataEstoque(quantidade);
        if (abateu) {
            Usuario navegador = usuarios.findByEmail("everton@gmail.com").get();
            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoASertComprado, quantidade, navegador, gateway);
            manager.persist(novaCompra);
            emails.novaCompra(novaCompra);

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);

        }
            BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
            problemaComEstoque.reject(null, "Não foi possível realizar por conta do estoque");

            throw problemaComEstoque;
        }
    }
