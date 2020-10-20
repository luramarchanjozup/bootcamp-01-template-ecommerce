package com.zup.mercadolivre.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PurchaseForm;
import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.repositories.ProductRepository;
import com.zup.mercadolivre.repositories.PurchaseRepository;
import com.zup.mercadolivre.repositories.UserRepository;
import com.zup.mercadolivre.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PurchaseGatewayController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    //@Autowired
    //private MailService mailService;

    @PostMapping("/product/{id}/purchase")
    public ResponseEntity<?> newPurchase(@PathVariable Long id, @RequestBody @Valid PurchaseForm form,
        UriComponentsBuilder uriBuilder, HttpServletResponse response) {

        if (!form.getGateway().equals("paypal") && !form.getGateway().equals("pagseguro")) {
            return ResponseEntity.badRequest().body("No payment gateway informed, or not supported");
        }

        User loggedUser = userRepository.findByEmail(UserService.authenticated().getUsername()).orElseThrow(
            () -> new IllegalStateException("User not found or not logged in properly"));
        Product product = productRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("Product not found"));
        Purchase purchase = form.toPurchase(product, loggedUser);

        product.subQuantityInStock(form.getAmount());
        //mailService.newProductInterest(product.getOwner().getEmail(), product.getName(), form.getAmount());
        purchaseRepository.save(purchase);
        productRepository.save(product);

        
        if (form.getGateway().equals("paypal")) {
            URI redirectUrl = uriBuilder.path("/payment/paypal-response").build().toUri();

            URI uri = URI.create("paypal.com/" + product.getId() + "?redirectUrl=" + redirectUrl);

            response.setHeader("Location", uri.toString());
            return ResponseEntity.ok().build();
        } else if (form.getGateway().equals("pagseguro")) {
            URI redirectUrl = uriBuilder.path("/payment/pagseguro-response").build().toUri();

            URI uri = URI.create("pagseguro.com/?returnId=" + product.getId() + "&redirectUrl=" + redirectUrl);

            response.setHeader("Location", uri.toString());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
