package com.zup.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.enums.PaymentGateway;
import com.zup.mercadolivre.model.enums.PurchaseStatus;
import com.zup.mercadolivre.model.interfaces.ReturnPaymentGateway;
import com.zup.mercadolivre.model.products.Product;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Product product;
    @NotNull
    @ManyToOne
    private User buyer;
    @NotNull
    private Integer amount;
    @NotNull
    @Enumerated
    private PaymentGateway gateway;
    @NotNull
    private PurchaseStatus status;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
    private Set<Transaction> transactions = new HashSet<>();

    @Deprecated
    public Purchase() { }

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

    public String returnUrl(UriComponentsBuilder builder) {
        return this.gateway.createReturnUrl(this, builder);
    }

    public void addTransaction(@Valid ReturnPaymentGateway response) {
        Transaction newTransaction = response.toTransaction(this);
        Assert.isTrue(!this.transactions.contains(newTransaction), "This transaction is already registered");

        Assert.isTrue(successfullyCompletedTransactions().isEmpty(), "This purchase has already been successfully completed");

        this.transactions.add(newTransaction);
    }

    private Set<Transaction> successfullyCompletedTransactions() {
        Set<Transaction> successfulTransactions = this.transactions.stream().filter(Transaction::successfullyCompleted)
                .collect(Collectors.toSet());

        Assert.isTrue(successfulTransactions.size() <= 1, "There's more than one completed transaction on purchase " + this.id);
        return successfulTransactions;
    }

	public boolean finishedSuccessfully() {
		return !successfullyCompletedTransactions().isEmpty();
	}

}
