package com.zup.mercadolivre.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ImagemController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Uploader uploader;

    @PostMapping("produtos/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable Long id, @Valid NovaImagemRequest request) {
        Set<String> links = uploader.envia(request.getImagens());
        Produto produto = manager.find(Produto.class, id);
        produto.associaImagens(links);
        manager.merge(produto);
        return produto.toString();
    }
}
