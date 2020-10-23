package com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.controller.dto.UserDTO;
import com.zup.mercadolivre.model.enums.Profiles;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.lang.Assert;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;
    @NotNull
    private LocalDateTime timeOfCreation;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @Deprecated
    public User() {
        addProfile(Profiles.USER);
    }

    /**
     * 
     * @param email with email format
     * @param cleanPassword in plain text
     */
    public User(String email, CleanPassword cleanPassword) {
        Assert.isTrue(StringUtils.hasLength(email), "email can't be blank");
        Assert.notNull(cleanPassword, "the object of type ClearPassword can't be null");

        this.email = email;
        this.password = cleanPassword.hash();
        this.timeOfCreation = LocalDateTime.now();
        addProfile(Profiles.USER);
        // Used for testing
        addProfile(Profiles.ADMIN);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Profiles> getProfiles() {
        return profiles.stream().map(x -> Profiles.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profiles profile) {
        profiles.add(profile.getCode());
    }

    public UserDTO toDto() {
        return new UserDTO(this);
    }

	public LocalDateTime getTimeOfCreation() {
		return this.timeOfCreation;
	}
}
