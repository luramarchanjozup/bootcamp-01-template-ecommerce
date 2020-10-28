package br.com.zup.ecommerce.entities.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Contagem de carga intrínseca da classe:1
 */

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {}

    //1
    public Usuario(@NotBlank String login, @Valid @NotNull SenhaTratada senhaTratada) {
        this.login = login;
        this.senha = senhaTratada.getSenha();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) &&
                login.equals(usuario.login) &&
                senha.equals(usuario.senha) &&
                dataCadastro.equals(usuario.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, dataCadastro);
    }
}
