package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.Transaction;
import com.zup.mercadolivre.model.enums.TransactionStatus;
import com.zup.mercadolivre.model.interfaces.ReturnPaymentGateway;

public class PaypalResponse implements ReturnPaymentGateway {

    @NotBlank
    private Long paymentId;
    @NotNull @Min(0) @Max(1)
    private Integer status;

    public PaypalResponse(Long paymentId, Integer status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public Transaction toTransaction(Purchase purchase) {
		return new Transaction(this.status == 0 ? TransactionStatus.ERROR : TransactionStatus.SUCCESS, this.paymentId, purchase);
	}
}
