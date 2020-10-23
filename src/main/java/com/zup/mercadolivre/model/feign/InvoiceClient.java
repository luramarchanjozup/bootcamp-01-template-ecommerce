package com.zup.mercadolivre.model.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "invoice", url = "http://localhost:8080/")
public interface InvoiceClient {
    
    @RequestMapping(method = RequestMethod.POST, value = "/invoice?{purchaseId}&{buyerId}")
    String createInvoice(@PathVariable Long purchaseId, @PathVariable Long buyerId);
}
