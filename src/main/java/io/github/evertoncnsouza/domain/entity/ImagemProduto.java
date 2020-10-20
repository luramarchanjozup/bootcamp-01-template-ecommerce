package io.github.evertoncnsouza.domain.entity;

import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

//1 PCI;
@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @URL
    @NotBlank
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;
        ImagemProduto that = (ImagemProduto) o;
        return produto.equals(that.produto) &&
                getLink().equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, getLink());
    }
}
