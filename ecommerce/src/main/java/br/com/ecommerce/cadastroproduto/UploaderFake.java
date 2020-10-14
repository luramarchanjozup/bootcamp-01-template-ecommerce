package br.com.ecommerce.cadastroproduto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader {

    @Override
    public List<String> envia(List<MultipartFile> imagens) {

        List<String> links = imagens
                .stream()
                .map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
                .collect(Collectors.toList());

        return links;

    }
}
