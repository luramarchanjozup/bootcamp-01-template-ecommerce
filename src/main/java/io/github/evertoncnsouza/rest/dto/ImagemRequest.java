package io.github.evertoncnsouza.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @Size(min = 1)
    //@NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemRequest(@Size(min = 1) List<MultipartFile> imagens) {
        super();
        this.imagens = imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
