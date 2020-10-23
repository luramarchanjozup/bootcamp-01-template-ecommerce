package com.zup.mercadolivre.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Uploader {
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://bucket.io/" +
                        imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
