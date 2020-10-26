package com.zup.mercadolivre.model.interfaces;

import com.zup.mercadolivre.model.Purchase;

public interface PurchaseEventSuccess {
    
    void process(Purchase purchase);
}
