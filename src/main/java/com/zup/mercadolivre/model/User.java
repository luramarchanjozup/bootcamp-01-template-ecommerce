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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.timeOfCreation = LocalDateTime.now();
        addProfile(Profiles.USER);
        // Used for testing
        addProfile(Profiles.ADMIN);
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTimeOfCreation() {
        return this.timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
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
}
