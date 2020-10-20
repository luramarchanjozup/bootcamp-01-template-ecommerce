package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.PagseguroResponse;
import com.zup.mercadolivre.controller.form.PaypalResponse;
import com.zup.mercadolivre.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseResponseController {

    @Autowired
    private PaymentService paymentService;
    
    @PutMapping("/payment/paypal-response")
    public ResponseEntity<?> paypalResponse(@RequestBody @Valid PaypalResponse response) {
        paymentService.setGatewayResponseTime(response.getProductId());
        
        return paymentService.paymentResponse(response.isApproved(), response.getProductId());
    }

    @PutMapping("/payment/pagseguro-response")
    public ResponseEntity<?> pagseguroResponse(@RequestBody @Valid PagseguroResponse response) {
        paymentService.setGatewayResponseTime(response.getProductId());

        return paymentService.paymentResponse(response.isApproved(), response.getProductId());
    }
}
