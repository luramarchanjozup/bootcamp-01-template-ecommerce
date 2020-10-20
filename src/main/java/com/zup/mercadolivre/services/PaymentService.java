package com.zup.mercadolivre.services;

import com.zup.mercadolivre.model.Purchase;
import com.zup.mercadolivre.model.enums.PurchaseStatus;
import com.zup.mercadolivre.model.feign.InvoiceClient;
import com.zup.mercadolivre.model.feign.RankingClient;
import com.zup.mercadolivre.repositories.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private RankingClient rankingClient;

    private Purchase getPurchase(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> new IllegalStateException("Purchase not found"));
    }

    public void setGatewayResponseTime(Long id) {
        Purchase purchase = getPurchase(id);
        purchase.setPaymentReturn();
    }

    public ResponseEntity<?> paymentResponse(boolean isApproved, Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        if (isApproved) {
            invoiceClient.createInvoice(purchase.getId(), purchase.getBuyer().getId());
            rankingClient.updateRanking(purchase.getId(), purchase.getProduct().getOwner().getId());
            mailService.purchaseCompleted(purchase);

            purchase.setStatus(PurchaseStatus.SUCCESS);
            purchaseRepository.save(purchase);
            return ResponseEntity.ok().build();
        }

        if (!isApproved) {
            mailService.failedPurchase(purchase);

            purchase.setStatus(PurchaseStatus.FAILED);
            purchaseRepository.save(purchase);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
