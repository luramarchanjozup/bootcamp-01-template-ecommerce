package com.zup.mercadolivre.produto.opiniao;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;
import com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/opinioes")
    @Transactional
    public String cadastrarOpiniao(@Valid @RequestBody NovaOpiniaoRequest request, @PathVariable Long id,
                                 Authentication authentication) {
        Produto produto = manager.find(Produto.class, id);
        Usuario consumidor = usuarioRepository.findByEmail(authentication.getName());
        Opiniao novaOpiniao = request.toModel(produto, consumidor);
        manager.persist(novaOpiniao);
        return novaOpiniao.toString();
    }
}
