package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProdutoNovoRequest;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.service.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@RestController
@RequestMapping("/produtos/{id}")
public class ProdutoAtualizacaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private Uploader uploader;

    @PostMapping("/imagens")
    @Transactional
    //1
    public ResponseEntity<String> envioImagens(@PathVariable("id") Long id, @Valid ImagensNovoRequest novasImagens, @AuthenticationPrincipal UserDetails user) {

        //1
        UsuarioLogado userDetails = (UsuarioLogado) user;
        //1
        Produto produto = this.getProduto(id);

        //1
        if(!produto.isDonoLogado(manager, userDetails.getUsuario())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploader.enviaImagens(novasImagens.getImagens());
        produto.incluirImagens(links);

        manager.merge(produto);

        return ResponseEntity.ok(links.toString());
    }

    @PostMapping("/opinioes")
    @Transactional
    //1
    public ResponseEntity<String> envioOpnioes(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoProdutoNovoRequest novaOpiniao) {

        Produto produto = this.getProduto(id);
        produto.incluirOpinioes(novaOpiniao);
        manager.merge(produto);

        Long opiniaoId = produto.getOpinioes().get(produto.getOpinioes().size() - 1).getId();
        return ResponseEntity.ok("Opinião " + opiniaoId  +" cadastrada.");
    }


    private Produto getProduto(Long id) {
        Produto produto = manager.find(Produto.class, id);
        //1
        if(produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return produto;
    }
}
