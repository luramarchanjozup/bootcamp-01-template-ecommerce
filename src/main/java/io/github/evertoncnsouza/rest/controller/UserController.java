package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.domain.entity.User;
import io.github.evertoncnsouza.rest.dto.UserRequest;
import io.github.evertoncnsouza.validation.constraintvalidation.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EmailValidator emailValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @PostMapping
    @Transactional
    public String save (@RequestBody @Valid UserRequest request) {
        User user = request.toUser();
        manager.persist(user);
        return user.toString();
    }
}
