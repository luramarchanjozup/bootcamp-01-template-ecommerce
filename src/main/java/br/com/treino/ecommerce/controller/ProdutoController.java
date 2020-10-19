package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.request.NovoProdutoRequest;
import br.com.treino.ecommerce.shared.UsuarioLogado;
import br.com.treino.ecommerce.shared.validations.CaracteristicasIguaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CaracteristicasIguaisValidator caracteristicasIguaisValidator; //1

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(caracteristicasIguaisValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity novoProduto(@RequestBody @Valid NovoProdutoRequest request,
                                      @AuthenticationPrincipal UsuarioLogado usuarioLogado){ //2

        Usuario dono = usuarioLogado.get(); //3

        Produto novoProduto = request
                .toProduto(entityManager, dono); //4

        entityManager.persist(novoProduto);

        return new ResponseEntity(HttpStatus.CREATED);
    }



}
