package br.com.ecommerce.cadastroproduto;
import antlr.Token;
import br.com.ecommerce.seguranca.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/imagens/{produtoId}")
public class ImagemController {


    @Autowired
    private EntityManager entityManager;


    @Autowired
    private UploaderFake uploaderFake;


    @Autowired
    private TokenManager tokenManager;


    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarFotos(@PathVariable Long produtoId, AdicionarImagemRequest arquivosEnviados,
                                            HttpServletRequest request ) {


        Produto produto = entityManager.find(Produto.class, produtoId);


        if(donoDoProduto(request, produto)){

            List<MultipartFile> imagens = arquivosEnviados.getArquivos();

            List<String> listaLinks = uploaderFake.envia(imagens);

            produto.associaImagens(listaLinks);

            entityManager.merge(produto);

            return ResponseEntity
                    .ok()
                    .build();

        }

        return ResponseEntity.badRequest().build();

    }



    public boolean donoDoProduto(HttpServletRequest request, Produto produto){


        String tokenDoUsuarioDaRequisicao = request.getHeader("Authorization");

        String emailDoUsuarioPeloToken = tokenManager.getUserName(tokenDoUsuarioDaRequisicao);

        String emailDoUsuarioPeloProdutoId = produto
                .getUsuario()
                .getLogin();

        return emailDoUsuarioPeloToken
                .equals(emailDoUsuarioPeloProdutoId);

    }


}
