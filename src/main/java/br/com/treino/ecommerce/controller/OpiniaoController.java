package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Opiniao;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.request.NovaOpiniaoRequest;
import br.com.treino.ecommerce.shared.UsuarioLogado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity novaOpiniao(@PathVariable("id") Long id,
                                      @RequestBody @Valid NovaOpiniaoRequest request,
                                      @AuthenticationPrincipal UsuarioLogado usuarioLogado){ //1 //2

        Optional<Produto> possivelProduto = Optional
                .ofNullable(entityManager.find(Produto.class, id)); //3

        if (possivelProduto.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND); //4
        }

        Usuario usuario = usuarioLogado.get(); //5

        Opiniao novaOpiniao = request.toOpiniao(possivelProduto.get(), usuario); //6

        entityManager.persist(novaOpiniao);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
