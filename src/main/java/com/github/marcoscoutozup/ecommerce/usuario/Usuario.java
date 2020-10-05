package com.github.marcoscoutozup.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @PastOrPresent
    @CreationTimestamp
    private LocalDateTime created_at;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = encriptarSenha(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String encriptarSenha(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
