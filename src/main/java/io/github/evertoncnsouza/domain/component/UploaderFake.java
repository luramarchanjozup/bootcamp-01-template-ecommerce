package io.github.evertoncnsouza.domain.component;

import io.github.evertoncnsouza.domain.repository.Uploader;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@Primary
public class UploaderFake implements Uploader {

    public Set<String> envia (List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://bucket.io/"
                        + imagem.getOriginalFilename())/* +"-"
                        + UUID.randomUUID().toString())*/
                .collect(Collectors.toSet());
    }
}
//@return link para imagens que foram uploads