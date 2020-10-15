package br.com.ecommerce.cadastroproduto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImagemUploader {

    public List<String> envia(List<MultipartFile> imagens) {

        return imagens
                .stream()
                .map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
                .collect(Collectors.toList());

    }
}
