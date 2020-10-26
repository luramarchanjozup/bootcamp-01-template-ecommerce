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

@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;
    private LocalDateTime instanteRegistro = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getInstanteRegistro() {
        return instanteRegistro;
    }

    public void setInstanteRegistro(LocalDateTime instanteRegistro) {
        this.instanteRegistro = instanteRegistro;
    }
}
