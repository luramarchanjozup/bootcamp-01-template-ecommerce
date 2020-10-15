package br.com.ecommerce.cadastrocategoria;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

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

}
