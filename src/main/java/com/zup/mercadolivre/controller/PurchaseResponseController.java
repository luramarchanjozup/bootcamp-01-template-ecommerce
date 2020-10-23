package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PagseguroResponse;
import com.zup.mercadolivre.controller.form.PaypalResponse;
import com.zup.mercadolivre.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//5
public class PurchaseResponseController {

    @Autowired
    //1
    private PaymentService paymentService;
    
    @PutMapping("/payment/paypal-response/{id}")
    //1
    public ResponseEntity<?> paypalResponse(@PathVariable Long id, @RequestBody @Valid /*1*/ PaypalResponse response) {        
        return paymentService.paymentResponse(response.isApproved(), response.getProductId());
    }

    @PutMapping("/payment/pagseguro-response/{id}")
    //1
    public ResponseEntity<?> pagseguroResponse(@PathVariable Long id, @RequestBody @Valid /*1*/ PagseguroResponse response) {
        return paymentService.paymentResponse(response.isApproved(), response.getProductId());
    }
}
