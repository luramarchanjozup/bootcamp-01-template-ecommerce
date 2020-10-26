package com.zup.mercadolivre.controller;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PurchaseForm;
import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
//5
public class PurchaseGatewayController {
    
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/product/{id}/purchase")
    @Transactional
    //1
    public ResponseEntity<?> newPurchase(@PathVariable Long id, @RequestBody @Valid PurchaseForm form,
        UriComponentsBuilder uriBuilder, HttpServletResponse response) throws BindException {
        
        //1
        Product product = manager.find(Product.class, id);
        //1
        User loggedUser = manager.createQuery("select u from User u where u.email = :email", User.class)
            .setParameter("email", UserService.authenticated().getUsername()).getSingleResult();
        
        //1
        if(product.subQuantityInStock(form.getAmount())) {
            //1
            Purchase purchase = form.toPurchase(product, loggedUser);
            manager.persist(purchase);
            
            response.setHeader("Location", purchase.returnUrl(uriBuilder));
            return ResponseEntity.ok().build();
            
        }

        BindException bindException = new BindException(form, "purchaseForm");
        bindException.reject(null, "It wasn't possible to complete the purchase," + 
            "the requested amount doesn't match the available stock");
        
        throw bindException;
    }
}
