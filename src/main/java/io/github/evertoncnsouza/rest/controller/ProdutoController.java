package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.Uploader;
import io.github.evertoncnsouza.rest.dto.ProdutoRequest;
import io.github.evertoncnsouza.domain.service.impl.UsuarioLogado;
import io.github.evertoncnsouza.validation.ProibeCaracteristicaComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//5 PCI's;
@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired //PCI 1;
    private Uploader uploaderFake;

    @InitBinder(value = "ProdutoRequest") //Fala que o método é aplicado só a classe ProdutoRequest;
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    } //PCI 2;

    @PostMapping
    @Transactional //PCI 3;
    public String save(@RequestBody @Valid ProdutoRequest request,
     @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Usuario dono = usuarioLogado.get();
         Produto produto = request.toModel(manager, dono);
         manager.persist(produto);
         return produto.toString();
    } //PCI 4 e PCI 5;

  }
