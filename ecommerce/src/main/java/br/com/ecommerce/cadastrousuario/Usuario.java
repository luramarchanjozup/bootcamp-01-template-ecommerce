package br.com.ecommerce.cadastrousuario;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Produto;
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

    /* @complexidade = acoplamento contextual */
    @OneToMany(mappedBy = "usuario")
    private List<Opiniao> opinioes;

    /* @complexidade = acoplamento contextual */
    @OneToMany(mappedBy = "usuario")
    private List<Produto> produtos;

    @Deprecated
    public Usuario(){};

    public Usuario(@NotBlank @Email String login, @NotBlank SenhaLimpa senhaLimpa) {

        this.login = login;

        this.senha = senhaLimpa.encoda();

        this.instanteCadastro = OffsetDateTime.now();

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

    public OffsetDateTime getInstanteCadastro() {
        return instanteCadastro;
    }


}
