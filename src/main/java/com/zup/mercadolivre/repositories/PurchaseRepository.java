package com.zup.mercadolivre.repositories;

import com.zup.mercadolivre.model.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
}
