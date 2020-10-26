package com.zup.mercadolivre.model.interfaces;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.Transaction;

public interface ReturnPaymentGateway {
    
    /**
     * 
     * @param purchase
     * @return returns a new transaction based on the current gateway
     */
    Transaction toTransaction(Purchase purchase);
}
