package br.com.ecommerce.cadastroproduto;
import br.com.ecommerce.seguranca.AutorizacaoDonoProduto;
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
    private ImagemUploader imagemUploader;


    @Autowired
    private AutorizacaoDonoProduto autorizacaoDonoProduto;


    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarFotos(@PathVariable Long produtoId, AdicionarImagemRequest arquivosEnviados,
                                            HttpServletRequest request ) {


        Produto produto = entityManager.find(Produto.class, produtoId);


        if(autorizacaoDonoProduto.donoDoProduto(request, produto)){

            List<MultipartFile> imagens = arquivosEnviados.getArquivos();

            List<String> listaLinks = imagemUploader.envia(imagens);

            produto.associaImagens(listaLinks);

            entityManager.merge(produto);

            return ResponseEntity
                    .ok()
                    .build();

        }

        return ResponseEntity.badRequest().build();

    }

}
