package com.zup.mercadolivre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailToSeller(String sellerEmail, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("There's a new question on your product " + productName);
        message.setText("Someone has a question for you about " + productName + ".");
        mailSender.send(message);
    }
}
