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
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/produtos/{produtoId}/compras")
public class CompraController {


    private final EntityManager entityManager;

    /* @complexidade = acoplamento contextual */
    private final BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;

    /* @complexidade = acoplamento contextual */
    private final UsuarioRepository usuarioRepository;


    public CompraController(EntityManager entityManager, BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken,
                            UsuarioRepository usuarioRepository) {
        this.entityManager = entityManager;
        this.buscaEmailDoUsuarioPeloToken = buscaEmailDoUsuarioPeloToken;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, HttpServletRequest request,
                                     @PathVariable Long produtoId, UriComponentsBuilder uriComponentsBuilder){


        var quantidadeSolicitada = compraRequest.getQuantidade();
        var produtoASerComprado = entityManager.find(Produto.class, produtoId);


        if(produtoASerComprado.verificaDisponibilidadeEAtualiza(quantidadeSolicitada)){

            String emailComprador = buscaEmailDoUsuarioPeloToken.buscaEmailDoUsuario(request);
            var comprador = usuarioRepository.findByLogin(emailComprador);
            var compra = compraRequest.toModel(entityManager, comprador);
            entityManager.persist(compra);
                                                                        
            String urlRedirecionamento = compra.urlRedirecionamento(uriComponentsBuilder);

            return ResponseEntity
                    .ok(urlRedirecionamento);

        }

        return ResponseEntity.badRequest().build();

    }
}
