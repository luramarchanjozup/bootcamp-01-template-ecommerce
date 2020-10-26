package com.zup.mercadolivre.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductQuestions;
import com.zup.mercadolivre.services.UserService;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * Handles the incoming {@link Category} creation information.
 * 
 * @author Matheus
 */
public class QuestionForm {
    
    @NotNull @NotBlank
    private String title;

    public QuestionForm(){}

    public QuestionForm(String title) {
        this.title = title;
    }

    /**
     * Creates a new {@link ProductQuestions} and checks if the 
     * logged {@link User} if the owner of the {@link Product}.
     * 
     * @throws BadCredentialsException if the logged User is he owner of the Product
     * 
     * @param manager cannot be null
     * @param product cannot be null
     * @return new {@link ProductQuestions}
     */
    public ProductQuestions toQuestion(EntityManager manager, Product product) {
        User loggedUser = manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
            .setParameter("email", UserService.authenticated().getUsername()).getSingleResult();

        product.checkOwnershipTrue(loggedUser.getEmail(), "The product owner can't post questions.");

        return new ProductQuestions(this.title, loggedUser, product);
    }
}
