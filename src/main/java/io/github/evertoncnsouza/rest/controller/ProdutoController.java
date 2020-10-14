package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.domain.repository.Uploader;
import io.github.evertoncnsouza.domain.repository.Users;
import io.github.evertoncnsouza.rest.dto.ImagemRequest;
import io.github.evertoncnsouza.rest.dto.ProdutoRequest;
import io.github.evertoncnsouza.validation.constraintvalidation.ProibeCaracteristicaComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

//7 PCI's;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Users users;
    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "ProdutoRequest") //Fala que o método é aplicado só a classe ProdutoRequest;
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public String save(@RequestBody @Valid ProdutoRequest request){
        User dono = users.findByEmail("everton@gmail.com").get();
         Produto produto = request.toModel(manager, dono);
         manager.persist(produto);
         return produto.toString();
    }

    @PostMapping("{id}/imagens")
    @Transactional
    public String saveImagens (@PathVariable("id") Long id, @Valid ImagemRequest request){

        User dono = users.findByEmail("everton@gmail.com").get();
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
