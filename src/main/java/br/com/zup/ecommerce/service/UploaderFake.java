package br.com.zup.ecommerce.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Contagem de carga intrínseca da classe: 1
 */

@Component
@Primary
public class UploaderFake implements Uploader {
    @Override
    public Set<String> enviaImagens(List<MultipartFile> imagens) {
        //1
        return imagens.stream()
                .map(imagem -> "http://bucket.io/" + UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
