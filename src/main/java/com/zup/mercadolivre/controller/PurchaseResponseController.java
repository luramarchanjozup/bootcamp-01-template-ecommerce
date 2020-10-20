package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PaypalResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseResponseController {
    
    @PutMapping("/paypal/payment")
    public ResponseEntity<?> paypalResponse(@RequestBody @Valid PaypalResponse response) {
        
    }
}
