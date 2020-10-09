package br.com.ecommerce.cadastroproduto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    @PutMapping
    public ResponseEntity<?> adicionarFotoLocal(AdicionarImagemRequest arquivoEnviado) throws IOException {

        MultipartFile arquivo = arquivoEnviado.getArquivo();

        String nomeDoArquivo = arquivo.getOriginalFilename();

        var diretorioParaSalvarImagemProduto =
                Path.of("/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/" +
                        "ecommerce/src/main/resources/static/imagens", nomeDoArquivo);

        arquivo.transferTo(diretorioParaSalvarImagemProduto);

        return ResponseEntity.ok().build();

    }
}
