package io.github.evertoncnsouza.domain.entity;

import io.github.evertoncnsouza.rest.dto.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

//1 PCI;
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @Length(min = 6)
    private String senha;

    @PastOrPresent
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@Email @NotBlank String email,
                   @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email),"email não pode ser em branco");
        Assert.notNull(senhaLimpa,"o objeto do tipo senha limpa nao pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
        this.instanteCriacao = LocalDateTime.now();
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getEmail(), usuario.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha
                + "]";
    }
}
