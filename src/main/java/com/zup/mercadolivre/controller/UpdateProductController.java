package com.zup.mercadolivre.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles the {@link Product} image upload process.
 * 
 * <p>The images como from a list of MultipartFiles, then they are
 * processed and saved in the "assets" folder. There, each product
 * is identified by "product_" followed by it's id. Inside, each image is
 * also identified by it's position in the array.
 * 
 * <p>The images are saved in a url in the Product database table.
 * 
 * @author Matheus Santos
 */
@RestController
// 4
public class UpdateProductController {

    @Autowired
    private EntityManager manager;

    @PutMapping("/product/{id}")
    @Transactional
    // 1
    public ResponseEntity<?> productImages(@PathVariable Long id, @RequestBody List<MultipartFile> images) {
        // 1
        Product product = manager.find(Product.class, id);
        product.checkOwnershipFalse(UserService.authenticated().getUsername(), "This user does not own the product");
        boolean noErrorsOcurred = product.saveImages(images);
        //1
        if (noErrorsOcurred) {
            manager.persist(product);
            return ResponseEntity.ok().body(product.toDto());
        } /*1*/ else {
            return ResponseEntity.badRequest().build();
        }
    }
}
