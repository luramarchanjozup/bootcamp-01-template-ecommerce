package com.zup.mercadolivre.controller.dto;

import java.time.LocalDateTime;

import com.zup.mercadolivre.model.User;

public class UserDTO {
    private Long id;
    private String email;
    private LocalDateTime timeOfCreation;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.timeOfCreation = user.getTimeOfCreation();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimeOfCreation() {
        return this.timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

}
