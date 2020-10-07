package br.com.ecommerce.cadastrousuario;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    private OffsetDateTime instanteCadastro;

    @Deprecated
    public Usuario(){};

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id.equals(usuario.id);
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
