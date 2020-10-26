package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.Transaction;
import com.zup.mercadolivre.model.enums.PagseguroStatus;
import com.zup.mercadolivre.model.interfaces.ReturnPaymentGateway;

public class PagseguroResponse implements ReturnPaymentGateway {
    
    @NotBlank
    private Long paymentId;
    @NotNull
    private PagseguroStatus status;

    public PagseguroResponse(Long paymentId, PagseguroStatus status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

	public Transaction toTransaction(Purchase purchase) {
		return new Transaction(this.status.normalize(), this.paymentId, purchase);
	}
}
