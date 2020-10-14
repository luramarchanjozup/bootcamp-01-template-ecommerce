package com.zup.mercadolivre.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.zup.mercadolivre.config.security.UserSS;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductImages;
import com.zup.mercadolivre.repositories.ProductRepository;
import com.zup.mercadolivre.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UpdateProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @PutMapping("/product/{id}")
    public ResponseEntity<?> produtImages(@PathVariable Long id, @RequestBody List<MultipartFile> images) {
        UserSS loggedUser = UserService.authenticated();
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product not found"));
        product.checkOwnership(loggedUser.getUsername());

        String assetsPath = Paths.get("src\\main\\java\\com\\zup\\mercadolivre\\assets").toAbsolutePath().toString();
        File imgFolder = new File(assetsPath + "\\" + "product_" + product.getId());
        imgFolder.mkdir();

        for (MultipartFile img : images) {
            try {
                File imgFile = new File(assetsPath + "\\" + "product_" + product.getId() + "\\" + product.getId() + "_img" + 
                        (product.getImages().size() + 1) + "." + img.getOriginalFilename().split("\\.")[1]);
                img.transferTo(imgFile);
                product.setImages(new ProductImages(imgFile.toPath().toAbsolutePath().toString(), product));
            } catch (IOException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        productRepository.save(product);
        return ResponseEntity.ok().body(product.toDto());
    }
}
