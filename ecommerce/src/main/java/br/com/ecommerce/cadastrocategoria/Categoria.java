package br.com.ecommerce.cadastrocategoria;
import br.com.ecommerce.validacao.Unico;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    /* @complexidade = acoplamento contextual */
    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria(){};


    public Categoria(String nome){
        this.nome = nome;
    }

    public Categoria(@NotBlank String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoria = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
