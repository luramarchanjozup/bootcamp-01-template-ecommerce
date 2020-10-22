package io.github.evertoncnsouza.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//Não tem PCI
    @Entity
    public class Categoria {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "{nome.categoria.obrigatorio}")
        @Column(unique = true)
        private String nome;

        @ManyToOne
        private Categoria categoriaMae;

        @Deprecated
        public Categoria() {
        }

        public Categoria(@NotEmpty(message = "{nome.categoria.obrigatorio}") String nome)
        {
            this.nome = nome;
        }

        public void setMae(Categoria categoriaMae) {
            this.categoriaMae = categoriaMae;
        }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

        @Override
        public String toString() {
            return "Categoria{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", categoriaMae=" + categoriaMae +
                    '}';
        }

}

