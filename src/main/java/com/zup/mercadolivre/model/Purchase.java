package com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String gateway;
    @NotNull
    private PurchaseStatus status;

    @Deprecated
    public Purchase(){}

    public Purchase(Product product, User buyer, Integer amount, String gateway) {
        this.product = product;
        this.buyer = buyer;
        this.amount = amount;
        this.gateway = gateway;
        this.status = PurchaseStatus.INITIATED;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PurchaseStatus getStatus() {
        return this.status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public String getGateway() {
        return this.gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

}
