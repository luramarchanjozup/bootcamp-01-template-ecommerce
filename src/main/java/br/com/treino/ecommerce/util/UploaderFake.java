package br.com.treino.ecommerce.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UploaderFake {

    public Set<String> enviar(List<MultipartFile> imagens) {

        return imagens.stream().map(imagem -> "http://bucket.io/"+
                imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
