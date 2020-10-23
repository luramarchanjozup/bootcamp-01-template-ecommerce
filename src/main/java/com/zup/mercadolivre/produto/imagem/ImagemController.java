package com.zup.mercadolivre.produto.imagem;

import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;
import com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ImagemController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploader;

    @PostMapping("produtos/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable Long id, @Valid NovaImagemRequest request, Authentication authentication) {
        Set<String> links = uploader.envia(request.getImagens());
        Usuario dono = usuarioRepository.findByEmail(authentication.getName());
        Produto produto = manager.find(Produto.class, id);
        if (!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.associaImagens(links);
        manager.merge(produto);
        return produto.toString();
    }
}
