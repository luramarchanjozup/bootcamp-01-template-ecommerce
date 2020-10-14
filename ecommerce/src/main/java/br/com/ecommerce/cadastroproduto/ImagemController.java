package br.com.ecommerce.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/imagens/{produtoId}")
public class ImagemController {


    @Autowired
    private EntityManager entityManager;


    @Autowired
    private UploaderFake uploaderFake;


    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarFotos(@PathVariable Long produtoId, AdicionarImagemRequest arquivosEnviados) {

        Produto produto = entityManager.find(Produto.class, produtoId);

        List<MultipartFile> imagens = arquivosEnviados.getArquivos();

        List<String> listaLinks = uploaderFake.envia(imagens);

        produto.associaImagens(listaLinks);

        entityManager.merge(produto);

        return ResponseEntity
                    .ok()
                    .build();

    }
}
