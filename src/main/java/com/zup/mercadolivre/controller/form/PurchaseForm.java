package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.enums.PaymentGateway;
import com.zup.mercadolivre.model.products.Product;

public class PurchaseForm {
    
    @NotNull @Positive
    private Integer amount;
    @NotNull
    private PaymentGateway gateway;

    public PurchaseForm(){}

    public PurchaseForm (Integer amount, PaymentGateway gateway) {
        this.amount = amount;
        this.gateway = gateway;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public PaymentGateway getGateway() {
        return this.gateway;
    }

    public Purchase toPurchase(Product product, User buyer) {
        return new Purchase(product, buyer, this.amount, this.gateway);
    }
}
