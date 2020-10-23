package com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.enums.PaymentGateway;
import com.zup.mercadolivre.model.enums.PurchaseStatus;
import com.zup.mercadolivre.model.products.Product;

@Entity
public class Purchase {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @ManyToOne
    private Product product;
    @NotNull @ManyToOne
    private User buyer;
    @NotNull
    private Integer amount;
    @NotNull @Enumerated
    private PaymentGateway gateway;
    @NotNull
    private PurchaseStatus status;
    
    private LocalDateTime paymentReturn;

    @Deprecated
    public Purchase(){}

    public Purchase(Product product, User buyer, Integer amount, PaymentGateway gateway) {
        this.product = product;
        this.buyer = buyer;
        this.amount = amount;
        this.gateway = gateway;
        this.status = PurchaseStatus.INITIATED;
    }

    public Long getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentReturn() {
        return this.paymentReturn;
    }

    public void setPaymentReturn() {
        this.paymentReturn = LocalDateTime.now();
    }

}
