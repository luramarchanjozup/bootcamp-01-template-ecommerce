package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;
import br.com.ecommerce.cadastrousuario.UsuarioRepository;
import br.com.ecommerce.seguranca.BuscaEmailDoUsuarioPeloToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, HttpServletRequest request){


        Long produtoId = compraRequest.getProdutoId();

        Long quantidadeSolicitada = compraRequest.getQuantidade();

        Produto produtoASerComprado = entityManager.find(Produto.class, produtoId);

        if(produtoASerComprado.verificaDisponibilidade(quantidadeSolicitada)){


            produtoASerComprado.atualizaDisponibilidadeEmEstoque(quantidadeSolicitada);


            String emailComprador = buscaEmailDoUsuarioPeloToken.buscaEmailDoUsuario(request);


            Usuario comprador = usuarioRepository
                    .findByLogin(emailComprador).orElseThrow();


            Compra compra = new Compra(compraRequest.getQuantidade(), produtoASerComprado, comprador);


            entityManager.persist(compra);


            return ResponseEntity.ok().build();


        }

        return ResponseEntity.badRequest().build();

    }

}
