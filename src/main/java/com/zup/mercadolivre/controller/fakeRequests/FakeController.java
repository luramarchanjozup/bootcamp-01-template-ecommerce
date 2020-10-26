package com.zup.mercadolivre.controller.fakeRequests;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {
    
    @PostMapping("/invoice")
    public ResponseEntity<?> fakeInvoiceClient(@RequestBody @Valid NewPurchaseInvoice request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> fakeRankingClient(@RequestBody @Valid NewPurchaseRanking request) {
        return ResponseEntity.ok().build();
    }
}
