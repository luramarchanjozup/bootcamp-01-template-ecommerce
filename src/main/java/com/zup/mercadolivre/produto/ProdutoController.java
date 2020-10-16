package com.zup.mercadolivre.produto;

import com.zup.mercadolivre.usuario.Usuario;
import com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaDuplicadaValidator());
    }

    @PostMapping
    @Transactional
    public String cadastrarProduto(@Valid @RequestBody NovoProdutoRequest request, Authentication authentication) {
        Usuario dono = usuarioRepository.findByEmail(authentication.getName());
        Produto produto = request.toModel(manager, dono);
        manager.persist(produto);
        return produto.toString();
    }
}
