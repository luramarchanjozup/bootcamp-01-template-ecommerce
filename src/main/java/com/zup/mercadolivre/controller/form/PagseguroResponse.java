package com.zup.mercadolivre.controller.form;

import com.zup.mercadolivre.model.enums.PagseguroStatus;

public class PagseguroResponse {
    
    private Long productId;
    private Long paymentId;
    private PagseguroStatus status;

    public PagseguroResponse(Long productId, Long paymentId, PagseguroStatus status) {
        this.productId = productId;
        this.paymentId = paymentId;
        this.status = status;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public boolean isApproved() {
        if (this.status.equals(PagseguroStatus.SUCESSO)) {
            return true;
        } else if (this.status.equals(PagseguroStatus.ERRO)) {
            return false;
        } else {
            throw new IllegalArgumentException("Unknown status code");
        }
    }
}
