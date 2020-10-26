package com.zup.mercadolivre.controller.dto;

public class OpinionDTO {
    private Long id;
    private Integer note;
    private String title;
    private String description;
    private UserDTO opinionOwner;

    public OpinionDTO(Long id, Integer note, String title, String description, UserDTO owner) {
        this.id = id;
        this.note = note;
        this.title = title;
        this.description = description;
        this.opinionOwner = owner;
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
