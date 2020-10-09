package com.zup.mercadolivre.controller.dto;

public class CredentialsDto {
    
    private String email;
    private String password;

    public CredentialsDto() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
