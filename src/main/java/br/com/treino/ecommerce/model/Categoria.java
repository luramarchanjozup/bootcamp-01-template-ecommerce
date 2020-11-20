package br.com.treino.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", categoria='" + nome + '\'' +
                ", categoriaMae='" + categoriaMae + '\'' +
                '}';
    }

}
