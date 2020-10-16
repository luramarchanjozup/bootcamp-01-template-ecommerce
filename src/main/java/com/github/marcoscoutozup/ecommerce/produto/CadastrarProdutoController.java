package com.github.marcoscoutozup.ecommerce.produto;

import com.github.marcoscoutozup.ecommerce.seguranca.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class CadastrarProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired //1
    private JwtUtils jwtUtils;

    @PostMapping
    @Transactional                                      //2
    public String cadastrarProduto(@RequestBody @Valid ProdutoDTO dto, HttpServletRequest request){
        String email = jwtUtils.capturarEmailDoUsuarioLogadoNoToken(request);

        //3
        Produto produto = dto.toModel(entityManager, email);
        entityManager.persist(produto);
        return produto.toString();
    }

}
