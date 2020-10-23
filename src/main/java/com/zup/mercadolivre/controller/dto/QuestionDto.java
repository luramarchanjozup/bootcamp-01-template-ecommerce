package com.zup.mercadolivre.controller.dto;

public class QuestionDto {
    
    private String title;
    private UserDTO owner;

    public QuestionDto(String title, UserDTO owner) {
        this.title = title;
        this.owner = owner;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getOwner() {
        return this.owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

}
