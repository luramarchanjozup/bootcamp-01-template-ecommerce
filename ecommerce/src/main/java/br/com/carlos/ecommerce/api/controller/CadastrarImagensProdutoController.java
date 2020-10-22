package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestImagemDto;
import br.com.carlos.ecommerce.config.UploaderFake;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.entity.Usuario;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;


@RestController
public class CadastrarImagensProdutoController {
    
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UploaderFake uploaderFake;

    @Transactional
    @PostMapping("produtos/{id}/imagens")
    public ResponseEntity<?> adicionar(@PathVariable("id") Long id, @Valid RequestImagemDto request) {
        Usuario dono = usuarioRepository.findByLogin("carlos@junior.com").get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return ResponseEntity.ok().build();
    }
    
}
