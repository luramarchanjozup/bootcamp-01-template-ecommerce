package com.zup.mercadolivre.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.CharacteristicsForm;
import com.zup.mercadolivre.controller.form.ProductForm;
import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.Product;
import com.zup.mercadolivre.model.ProductCharacteristics;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repositories.CategoryRepository;
import com.zup.mercadolivre.repositories.ProductRepository;
import com.zup.mercadolivre.repositories.UserRepository;
import com.zup.mercadolivre.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
        Category categoryObj = categoryRepository.findByName(form.getCategory()).orElseThrow(
            () -> new IllegalStateException("Category not found"));
        User userObj = userRepository.findByEmail(UserService.authenticated().getUsername()).orElseThrow(
            () -> new IllegalStateException("User not found or not logged in properly"));

            
        Product product = form.toProduct(categoryObj, userObj);

        List<ProductCharacteristics> characteristics = new ArrayList<>();

        for (CharacteristicsForm c : form.getCharacteristics()) {
            ProductCharacteristics pc = new ProductCharacteristics(c.getName(), c.getDescription(), product);
            characteristics.add(pc);
        }

        product.setCharacteristics(characteristics);

        productRepository.save(product);
        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(product);
    }
}
