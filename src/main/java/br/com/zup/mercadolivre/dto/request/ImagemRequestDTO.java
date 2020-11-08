package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ImagemRequestDTO {

    @NotEmpty
    private List<String> imagens;

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

}
