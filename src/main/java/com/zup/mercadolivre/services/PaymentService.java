package com.zup.mercadolivre.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.enums.PurchaseStatus;
import com.zup.mercadolivre.model.feign.InvoiceClient;
import com.zup.mercadolivre.model.feign.RankingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//7
public class PaymentService {
    
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private MailService mailService;
    @Autowired
    //1
    private InvoiceClient invoiceClient;
    @Autowired
    //1
    private RankingClient rankingClient;

    @Transactional
    //1
    public ResponseEntity<?> paymentResponse(boolean isApproved, Long purchaseId) {
        //1
        Purchase purchase = manager.find(Purchase.class, purchaseId);
        purchase.setPaymentReturn();

        //1
        if (isApproved) {
            /**
             * Comunicação com sistemas de nota fiscal, ranking e envio de email
             */
            //invoiceClient.createInvoice(purchase.getId(), purchase.getBuyer().getId());
            //rankingClient.updateRanking(purchase.getId(), purchase.getProduct().getOwner().getId());
            //mailService.purchaseCompleted(purchase);

            purchase.setStatus(PurchaseStatus.SUCCESS);
            manager.merge(purchase);
            return ResponseEntity.ok().build();
        }
        //1
        if (!isApproved) {
            /**
             * Envio de email
             */
            //mailService.failedPurchase(purchase);

            purchase.setStatus(PurchaseStatus.FAILED);
            manager.merge(purchase);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
