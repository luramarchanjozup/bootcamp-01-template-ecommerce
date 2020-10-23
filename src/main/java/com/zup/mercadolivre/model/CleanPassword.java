package com.zup.mercadolivre.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Represents a clear password in the system.
 */
public class CleanPassword {
    
    @NotBlank @Size(min = 6)
    private String password;

    public CleanPassword(String password) {
        Assert.hasLength(password, "password can't be blank");
        Assert.isTrue(password.length() >= 6, "password must have at least 6 characters");

        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.password);
    }
}
