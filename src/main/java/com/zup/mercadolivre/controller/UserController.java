package com.zup.mercadolivre.controller;

import javax.validation.Valid;

import com.zup.mercadolivre.config.security.UserSS;
import com.zup.mercadolivre.controller.form.UserForm;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.enums.Profiles;
import com.zup.mercadolivre.repositories.UserRepository;
import com.zup.mercadolivre.services.UserService;
import com.zup.mercadolivre.services.validations.CheckDuplicatedEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CheckDuplicatedEmail checkDuplicatedEmail;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(checkDuplicatedEmail);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserForm form) {
        User user = new User(form.getEmail(), encoder.encode(form.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> list(@PathVariable @Valid Long id) {
        UserSS user = UserService.authenticated();

        if (user == null || !user.hasRole(Profiles.ADMIN) && !id.equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        User userObj = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
        return ResponseEntity.ok().body(userObj.toDto());
    }
}
