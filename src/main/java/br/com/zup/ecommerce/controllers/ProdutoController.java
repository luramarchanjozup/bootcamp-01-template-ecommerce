package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.ProdutoNovoRequest;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProdutoNovoRequest;
import br.com.zup.ecommerce.service.Uploader;
import br.com.zup.ecommerce.validations.produto.CaracteristicasSemRepeticaoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 8
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private Uploader uploader;

    @InitBinder(value = "produtoNovoRequest")
    public void init(WebDataBinder binder) {
        //1
        binder.addValidators(new CaracteristicasSemRepeticaoValidacao());
    }

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<String> cadastroProdutos(@RequestBody @Valid ProdutoNovoRequest novoProduto) {
        //1
        Produto produto = novoProduto.toModel(manager);
        manager.persist(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto " +produto.getId()+" cadastrado.");
    }

    @PostMapping("{id}/imagens")
    @Transactional
    //1
    public ResponseEntity<String> envioImagens(@PathVariable("id") Long id, @Valid ImagensNovoRequest novasImagens) {

        Set<String> links = uploader.enviaImagens(novasImagens.getImagens());

        Produto produto = this.getProduto(id);

        produto.incluirImagens(links);

        //1
        if(!produto.isDonoLogado(manager)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        manager.merge(produto);

        return ResponseEntity.ok(links.toString());
    }

    @PostMapping("{id}/opinioes")
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
