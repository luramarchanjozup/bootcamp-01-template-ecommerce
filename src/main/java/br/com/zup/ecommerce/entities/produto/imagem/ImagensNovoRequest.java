package br.com.zup.ecommerce.entities.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Contagem de carga intrínseca da classe: 0
 */

public class ImagensNovoRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

    public ImagensNovoRequest(@Size(min = 1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }
}
