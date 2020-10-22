package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;
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

    @Autowired
    private EntityManager entityManager;

    //1
    @Autowired
    private BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;

    //1
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional                                                              //1
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, HttpServletRequest request,
                                     @PathVariable Long produtoId, UriComponentsBuilder uriComponentsBuilder){


        Long quantidadeSolicitada = compraRequest.getQuantidade();

                        //1                
        Produto produtoASerComprado = entityManager.find(Produto.class, produtoId);

        //1                                    
        if(produtoASerComprado.verificaDisponibilidadeEAtualiza(quantidadeSolicitada)){

                                            //1
            String emailComprador = buscaEmailDoUsuarioPeloToken.buscaEmailDoUsuario(request);

                    //1
            Usuario comprador = usuarioRepository.findByLogin(emailComprador);

                    //1
            Compra compra = compraRequest.toModel(entityManager, comprador);

            entityManager.persist(compra);
               
            String urlRedirecionamento = compra.urlRedirecionamento(uriComponentsBuilder);

            return ResponseEntity
                    .ok(urlRedirecionamento);


        }

        return ResponseEntity.badRequest().build();

    }
}
