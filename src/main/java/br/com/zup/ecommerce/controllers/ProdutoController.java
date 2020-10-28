package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.ProdutoNovoRequest;
import br.com.zup.ecommerce.entities.produto.ProdutoRetornoDetalhe;
import br.com.zup.ecommerce.security.UsuarioLogado;
import br.com.zup.ecommerce.validations.produto.CaracteristicasSemRepeticaoValidacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Contagem de carga intrínseca da classe: 6
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @InitBinder(value = "produtoNovoRequest")
    public void init(WebDataBinder binder) {
        //1
        binder.addValidators(new CaracteristicasSemRepeticaoValidacao());
    }

    @PostMapping
    @Transactional
    //1
    public ResponseEntity<String> cadastroProdutos(@RequestBody @Valid ProdutoNovoRequest novoProduto, @AuthenticationPrincipal UserDetails user) {

        //1
        UsuarioLogado userDetails = (UsuarioLogado) user;

        //1
        Produto produto = novoProduto.toModel(manager, userDetails.getUsuario());
        manager.persist(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto " +produto.getId()+" cadastrado.");
    }

    @GetMapping("/{id}")
    //1
    public ResponseEntity<ProdutoRetornoDetalhe> detalhesProduto(@PathVariable("id") Long id){

        Produto produto = manager.find(Produto.class, id);
        //1
        if(produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ProdutoRetornoDetalhe produtoRetornoDetalhe = new ProdutoRetornoDetalhe(produto);

        return ResponseEntity.ok(produtoRetornoDetalhe);
    }

}
