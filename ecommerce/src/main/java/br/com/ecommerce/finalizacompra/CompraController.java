package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.UsuarioRepository;
import br.com.ecommerce.seguranca.BuscaEmailDoUsuarioPeloToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("produtos/{produtoId}/compras")
public class CompraController {

    //pci = 9

    @Autowired
    private EntityManager entityManager;

    //1 - acoplamento
    @Autowired
    private BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;

    //1 - acoplamento
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional                                   //1 - classe específica do projeto
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, HttpServletRequest request,
                                     @PathVariable Long produtoId, UriComponentsBuilder uriComponentsBuilder){


        Long quantidadeSolicitada = compraRequest.getQuantidade();

        //1 - classe específica do projeto
        Produto produtoASerComprado = entityManager.find(Produto.class, produtoId);

        //1 - if
        if(produtoASerComprado.verificaDisponibilidadeEAtualiza(quantidadeSolicitada)){

            String emailComprador = buscaEmailDoUsuarioPeloToken
                    .buscaEmailDoUsuario(request);

            //1 - classe específica do projeto
            Compra compra = compraRequest.toModel(
                    entityManager,
                    usuarioRepository.findByLogin(emailComprador)  //1 - função como parâmetro
            );


            entityManager.persist(compra);


            return ResponseEntity       //1 - função como parâmetro
                    .ok(compra.urlRedirecionamento(uriComponentsBuilder));

        }

        return ResponseEntity
                .badRequest()
                .build();

    }
}
