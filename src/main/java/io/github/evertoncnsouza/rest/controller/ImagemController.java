package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.Uploader;
import io.github.evertoncnsouza.domain.repository.Usuarios;
import io.github.evertoncnsouza.rest.dto.ImagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

//6 PCI's;
@RestController
@RequestMapping("produtos/{id}/imagens")
public class ImagemController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired //PCI 1;
    private Usuarios usuarios;

    @Autowired //PCI 2;
    private Uploader uploaderFake;

   @PostMapping("{id}/imagens")
   @Transactional
    public String saveImagens (@PathVariable("id") Long id, @Valid ImagemRequest request){

        Usuario dono = usuarios.findByEmail("everton@gmail.com").get();
        Produto produto = manager.find(Produto.class,id);
        if(!produto.pertenceAoUser(dono)){
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links= uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return produto.toString();
    }
}
