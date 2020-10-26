package com.zup.mercadolivre.controller.fakeRequests;

import javax.validation.constraints.NotNull;

public class NewPurchaseRanking {
    @NotNull
    private Long purchaseId;
    @NotNull
    private Long sellerId;

    public NewPurchaseRanking(Long purchaseId, Long sellerId) {
        this.purchaseId = purchaseId;
        this.sellerId = sellerId;
    }
}
