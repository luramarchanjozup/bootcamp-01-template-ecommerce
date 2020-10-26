package com.zup.mercadolivre.services;

import com.zup.mercadolivre.model.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 
 * Handle all possible e-mail sends in the api.
 * 
 * <p> The file <code>application.properties</code> must have somethings set
 * in order for this to work.
 * <pre>
 *      Example:
 * 
 *      # mailSender
 *      spring.mail.host=smtp.gmail.com
 *      spring.mail.port=587
 *      spring.mail.username=username
 *      spring.mail.password=password
 *      spring.mail.protocol=smtp
 *
 *      spring.mail.properties.mail.smtp.auth=true
 *      spring.mail.properties.mail.smtp.starttls.enable=true
 * </pre>
 * 
 * @author Matheus
 *   
 */
@Service
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void newQuestionToSeller(String sellerEmail, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("There's a new question on your product " + productName);
        message.setText("Someone has a question for you about " + productName + ".");
        mailSender.send(message);
    }

    public void newProductInterest(String sellerEmail, String productName, Integer amount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("There's someone interested in " + productName);
        message.setText("Someone just initiated a purchase process in " + amount + productName + ".");
        mailSender.send(message);
    }

    public void purchaseCompleted(Purchase purchase) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(purchase.getBuyer().getEmail());
        message.setSubject("Purchase successful.");
        message.setText("Your recent purchase for " + purchase.getProduct().getName() + " has been successfully completed.\n" +
        "Information about the purchase:\n" + 
        "Product: " + purchase.getProduct().getName() + "\n" +
        "Amount: " + purchase.getAmount() + "\n");
        mailSender.send(message);
    }

    public void failedPurchase(Purchase purchase) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(purchase.getBuyer().getEmail());
        message.setSubject("Something went wrong with you recent payment.");
        message.setText("Something went wrong with your recent payment for " + purchase.getProduct().getName() + ".\n" +
        "Click here {link} to try again.");
        mailSender.send(message);
    }
}
