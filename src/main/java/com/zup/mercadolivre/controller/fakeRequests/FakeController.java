package com.zup.mercadolivre.controller.fakeRequests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {
    
    @PostMapping("/invoice?{purchaseId}&{buyerId}")
    public ResponseEntity<?> fakeInvoiceClient(@PathVariable Long purchaseId, @PathVariable Long buyerId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking?{purchaseId}&{sellerId}")
    public ResponseEntity<?> fakeRankinClient(@PathVariable Long purchaseId, @PathVariable Long sellerId) {
        return ResponseEntity.ok().build();
    }
}
