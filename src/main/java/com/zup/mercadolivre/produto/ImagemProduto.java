package com.zup.mercadolivre.produto;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @ManyToOne @NotNull @Valid Produto produto;
    private @URL @NotBlank String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return Objects.equals(produto, that.produto) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }
}
