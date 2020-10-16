package com.zup.mercadolivre.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductOpinion;
import com.zup.mercadolivre.services.UserService;

/**
 * Handles the incoming {@link ProductOpinion} creation information.
 * 
 * @author Matheus
 */

public class OpinionForm {
    
    @NotNull @Min(1) @Max(5)
    private Integer note;
    @NotNull @NotBlank
    private String title;
    @NotNull @NotBlank @Size(max = 500)
    private String description;

    public OpinionForm(Integer note, String title, String description) {
        this.note = note;
        this.title = title;
        this.description = description;
    }

    public Integer getNote() {
        return this.note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the logged user and creates a new {@link ProductOpinion}.
     * 
     * @throws NoResultException if no {@link User} is found. (Highly unlikely as the user 
     * information is from a logged one)
     * 
     * @param manager cannot be null
     * @param product cannot be null
     * @return new {@link ProductOpinion}
     */
	public ProductOpinion toOpinion(EntityManager manager, Product product) {
        User loggedUser = manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
            .setParameter("email", UserService.authenticated().getUsername()).getSingleResult();
		return new ProductOpinion(this.note, this.title, this.description, loggedUser, product);
	}

}
