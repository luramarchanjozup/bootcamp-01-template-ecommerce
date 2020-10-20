package com.zup.mercadolivre.controller.form;

public class PagseguroResponse {
    
    private Long productId;
    private Long paymentId;
    private String status;

    public PagseguroResponse(Long productId, Long paymentId, String status) {
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

    public String getStatus() {
        return this.status;
    }

    public boolean isApproved() {
        if (this.status.equals("SUCESSO")) {
            return true;
        } else if (this.status.equals("ERRO")) {
            return false;
        } else {
            throw new IllegalArgumentException("Unknown status code");
        }
    }
}
