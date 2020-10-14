package com.zup.mercadolivre.controller.dto;

import com.zup.mercadolivre.model.products.ProductOpinion;

public class OpinionDTO {
    private Long id;
    private Integer note;
    private String title;
    private String description;
    private UserDTO opinionOwner;

    public OpinionDTO(ProductOpinion opinion) {
        this.id = opinion.getId();
        this.note = opinion.getNote();
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
        this.opinionOwner = opinion.getOpnionOwner().toDto();
    }

    public Long getId() {
        return this.id;
    }

    public Integer getNote() {
        return this.note;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public UserDTO getOpinionOwner() {
        return this.opinionOwner;
    }

}
