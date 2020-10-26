package com.zup.mercadolivre.controller.fakeRequests;

import javax.validation.constraints.NotNull;

public class NewPurchaseInvoice {
    
    @NotNull
    private Long purchaseId;
    @NotNull
    private Long buyerId;

    public NewPurchaseInvoice(Long purchaseId, Long buyerId) {
        this.purchaseId = purchaseId;
        this.buyerId = buyerId;
    }
}
