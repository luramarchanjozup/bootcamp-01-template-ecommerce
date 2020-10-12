package br.com.ecommerce.cadastroproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/imagem/{produtoId}")
public class ImagemController {

    @Autowired
    private EntityManager entityManager;

    @PutMapping
    @Transactional
    public ResponseEntity<?> adicionarFotoLocal(@PathVariable Long produtoId,
                                                AdicionarImagemRequest arquivoEnviado) throws IOException {

       // 1
        entityManager.persist(arquivoEnviado.criaLinkDaImagem());

        // 1
        MultipartFile arquivo = arquivoEnviado.getArquivo();

        // 1
        String nomeDoArquivo = arquivo.getOriginalFilename();

        // 1
        var diretorioParaSalvarImagemProduto =
                Path.of("/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/" +
                        "ecommerce/src/main/resources/static/imagens", nomeDoArquivo);

        // 1
        arquivo.transferTo(diretorioParaSalvarImagemProduto);

        return ResponseEntity
                .ok()
                .build();

    }
}
