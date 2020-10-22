package br.com.zup.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private LocalDateTime instantRegistration = LocalDateTime.now();

    @Deprecated
    public User() {
    }

    public User(@NotBlank @Email String login, @NotBlank @Length(min = 6) String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getInstantRegistration() {
        return instantRegistration;
    }

    public void setInstantRegistration(LocalDateTime instantRegistration) {
        this.instantRegistration = instantRegistration;
    }
}
