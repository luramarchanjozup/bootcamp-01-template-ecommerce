package br.com.ecommerce.cadastroproduto;
import br.com.ecommerce.seguranca.AutorizacaoDonoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/imagens/{produtoId}")
public class ImagemController {


    private final EntityManager entityManager;

    private final ImagemUploader imagemUploader;

    private final AutorizacaoDonoProduto autorizacaoDonoProduto;


    public ImagemController(EntityManager entityManager, ImagemUploader imagemUploader, AutorizacaoDonoProduto autorizacaoDonoProduto) {
        this.entityManager = entityManager;
        this.imagemUploader = imagemUploader;
        this.autorizacaoDonoProduto = autorizacaoDonoProduto;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarFotos(@PathVariable Long produtoId, @Valid AdicionarImagemRequest arquivosEnviados,
                                            HttpServletRequest request) {

        var produto = entityManager.find(Produto.class, produtoId);
        if(!autorizacaoDonoProduto.donoDoProduto(request, produto)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        var imagens = arquivosEnviados.getArquivos();
        var listaLinks = imagemUploader.envia(imagens);
        produto.associaImagens(listaLinks);
        entityManager.merge(produto);


        return ResponseEntity
                .ok()
                .build();

    }
}
