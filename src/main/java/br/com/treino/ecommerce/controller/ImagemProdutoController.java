package br.com.treino.ecommerce.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/produtos")
public class ImagemProdutoController {

    @PostMapping("/{id}/imagens")
    public String novaImagemProduto(@PathVariable("id") Long id, MultipartFile file){
        return file.getOriginalFilename();
    }

}
