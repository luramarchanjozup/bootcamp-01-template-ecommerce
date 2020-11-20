package br.com.treino.ecommerce.model;

import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class ImagemProduto {

    @NotBlank @URL private String link;

    @Deprecated
    public ImagemProduto(){}

    public ImagemProduto(@NotBlank @URL String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "link='" + link + '\'' +
                '}';
    }

}
