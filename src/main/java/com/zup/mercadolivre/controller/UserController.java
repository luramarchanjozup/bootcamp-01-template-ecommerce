package com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import com.zup.mercadolivre.controller.form.UserForm;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.services.validations.CheckDuplicatedEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the {@link User} creation. It also encrypts the
 * password using {@link BCryptPasswordEncoder}.
 * 
 * @author Matheus Santos
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EntityManager manager;
    @Autowired
    private CheckDuplicatedEmail checkDuplicatedEmail;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(checkDuplicatedEmail);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserForm form) {
        manager.persist(form.toUser(encoder));

        return ResponseEntity.ok().build();
    }

}
