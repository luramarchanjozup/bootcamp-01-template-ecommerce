package com.zup.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;
    private @NotBlank @Email String email;
    private @NotBlank @Length(min = 6) String senha;
    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = encriptarSenha(senha);
    }

    public String encriptarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
