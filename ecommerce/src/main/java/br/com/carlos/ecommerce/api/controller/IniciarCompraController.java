package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestCompraDto;
import br.com.carlos.ecommerce.config.security.TokenManager;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import br.com.carlos.ecommerce.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class IniciarCompraController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired              //1
    private TokenManager tokenManager;
    @Autowired                  //1
    private UsuarioRepository usuarioRepository;
    @Autowired      //1
    EmailService emailService;

    @Transactional
    @PostMapping("compras")                                          //1
    public ResponseEntity<?> finalizarCompra(@Valid @RequestBody RequestCompraDto request, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest servletRequest) {
        var usuarioLogado = tokenManager.getUserName(servletRequest.getHeader("Authorization"));
        var comprador = usuarioRepository.findByLogin(usuarioLogado);
            //1
        var compra = request.toEntity(manager, comprador.get());
        var validarEstoque = compra.getProdutoEscolhido().abataEstoque(compra.getQuantidade());
        //1
        if (!validarEstoque) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto sem estoque");
        }
        manager.persist(compra);
        emailService.enviarEmailNovaCompra(compra);

        return ResponseEntity.status(302).body(compra.urlRedirecionamento(uriComponentsBuilder));
    }
}
