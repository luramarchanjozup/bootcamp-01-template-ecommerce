package com.zup.mercadolivre.services;

import java.util.Set;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.interfaces.PurchaseEventSuccess;

import org.springframework.beans.factory.annotation.Autowired;

public class NewPurchaseEvents {

    @Autowired
    private Set<PurchaseEventSuccess> purchaseEventSuccesses;
    @Autowired
    private MailService mailService;

	public void process(Purchase purchase) {
        if (purchase.finishedSuccessfully()) {
            purchaseEventSuccesses.forEach(event -> event.process(purchase));
            mailService.purchaseCompleted(purchase);
        } else {
            mailService.failedPurchase(purchase);
        }
	}
    
}
