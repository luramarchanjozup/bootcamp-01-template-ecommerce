package com.zup.mercadolivre.services.fakeSystems;

import java.util.Map;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.interfaces.PurchaseEventSuccess;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Invoice implements PurchaseEventSuccess {
    
    public void process(Purchase purchase) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("purchaseId", purchase.getId(), "buyerId", purchase.getBuyer().getId());
        
        restTemplate.postForEntity("http://localhost:8080/invoice", request, String.class);
    }
}
