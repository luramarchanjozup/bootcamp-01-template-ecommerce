package com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.enums.TransactionStatus;

@Entity
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private TransactionStatus status;
    @NotNull @NotBlank
    private Long paymentId;
    @NotNull
    private LocalDateTime instant;
    @ManyToOne
    private Purchase purchase;

    @Deprecated
    public Transaction() { }

	public Transaction(@NotNull TransactionStatus status, @NotBlank Long paymentId, Purchase purchase) {
        this.status = status;
        this.paymentId = paymentId;
        this.instant = LocalDateTime.now();
        this.purchase = purchase;
    }
    
    public boolean successfullyCompleted() {
        return this.status.equals(TransactionStatus.SUCCESS);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(id, transaction.id) && Objects.equals(status, transaction.status) && Objects.equals(paymentId, transaction.paymentId) && Objects.equals(instant, transaction.instant) && Objects.equals(purchase, transaction.purchase);
    }
}
