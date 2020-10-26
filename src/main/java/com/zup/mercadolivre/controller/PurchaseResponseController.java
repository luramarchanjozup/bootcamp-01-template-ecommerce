package com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PagseguroResponse;
import com.zup.mercadolivre.controller.form.PaypalResponse;
import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.interfaces.ReturnPaymentGateway;
import com.zup.mercadolivre.services.NewPurchaseEvents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//5
public class PurchaseResponseController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    //1
    private NewPurchaseEvents newPurchaseEvents;
    
    @PutMapping("/payment/paypal-response/{id}")
    //1
    public ResponseEntity<?> paypalResponse(@PathVariable Long id, @RequestBody @Valid PaypalResponse response) {     
        return process(id, response);
    }

    @PutMapping("/payment/pagseguro-response/{id}")
    @Transactional
    //1
    public ResponseEntity<?> pagseguroResponse(@PathVariable Long id, @RequestBody @Valid PagseguroResponse response) {
        return process(id, response);
    }
    //1
    private ResponseEntity<?> process(Long id, ReturnPaymentGateway returnPaymentGateway) {
        //1
        Purchase purchase = manager.find(Purchase.class, id);
        purchase.addTransaction(returnPaymentGateway);
        manager.merge(purchase);
        newPurchaseEvents.process(purchase);

        return ResponseEntity.ok().build();
    }
}
