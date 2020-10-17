package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.UsuarioRepository;
import io.github.evertoncnsouza.rest.dto.CompraRequest;
import io.github.evertoncnsouza.rest.dto.GatewayPagamento;
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

@RestController
@RequestMapping("compras")
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public String save(@RequestBody @Valid CompraRequest request, UriComponentsBuilder
            uriComponentsBuilder) throws BindException {

        Produto produtoASertComprado = manager.find(Produto.class,
                request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASertComprado.abataEstoque(quantidade);
        if (abateu) {
            Usuario navegador = usuarioRepository.findByEmail("everton@gmail.com").get();
            GatewayPagamento gateway = request.getGateway();
            Compra novaCompra = new Compra(produtoASertComprado, quantidade, navegador, request.getGateway());
            manager.persist(novaCompra);


            if (gateway.equals(GatewayPagamento.pagseguro)) {
                String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(novaCompra.getId()).toString();
                return "pagseguro.com/" + novaCompra.getId()
                        + "?redirectUrl=" + urlRetornoPagseguro;
            } else {
                String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com/" + novaCompra.getId()
                        + "?redirectUrl=" + urlRetornoPaypal;
            }
        }
            BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
            problemaComEstoque.reject(null, "Não foi possível realizar por conta do estoque");

            throw problemaComEstoque;
        }
    }
