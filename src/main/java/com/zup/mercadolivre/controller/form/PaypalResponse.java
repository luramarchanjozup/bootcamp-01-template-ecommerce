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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
