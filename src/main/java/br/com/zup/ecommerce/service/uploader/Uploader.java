package br.com.zup.ecommerce.service.uploader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {

    Set<String> enviaImagens(List<MultipartFile> imagens);
}
