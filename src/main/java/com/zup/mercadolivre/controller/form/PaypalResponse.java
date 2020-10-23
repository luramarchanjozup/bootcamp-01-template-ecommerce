package com.zup.mercadolivre.controller.form;

public class PaypalResponse {
    
    private Long productId;
    private Long paymentId;
    private Integer status;

    public PaypalResponse(Long productId, Long paymentId, Integer status) {
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
		if(this.status.equals(1)) {
            return true;
        } else if (this.status.equals(0)) {
            return false;
        } else {
            throw new IllegalArgumentException("Unknown status code");
        }
	}

}
