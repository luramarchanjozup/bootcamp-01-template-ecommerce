package com.zup.mercadolivre.controller.form;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;

public class PurchaseForm {
    
    private Integer amount;
    private String gateway;

    public PurchaseForm(){}

    public PurchaseForm (Integer amount, String gateway) {
        this.amount = amount;
        this.gateway = gateway;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGateway() {
        return this.gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Purchase toPurchase(Product product, User buyer) {
        return new Purchase(product, buyer, this.amount, this.gateway);
    }
}
