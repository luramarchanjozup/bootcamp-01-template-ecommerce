package br.com.ecommerce.cadastrousuario;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    @PastOrPresent
    private OffsetDateTime instanteCadastro;

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    @Deprecated
    public Usuario(){};

    public Usuario(@NotBlank @Email String login, @NotBlank String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.instanteCadastro = OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id.equals(usuario.id);
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(List<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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

    public OffsetDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(OffsetDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }
}
