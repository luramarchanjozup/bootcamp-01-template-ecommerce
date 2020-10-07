package com.github.marcoscoutozup.ecommerce.produto.adicionarimagem;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ImagemDTO {

    @NotEmpty
    private List<String> imagens;

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }
}
