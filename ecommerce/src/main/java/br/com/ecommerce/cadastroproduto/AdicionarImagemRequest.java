package br.com.ecommerce.cadastroproduto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AdicionarImagemRequest {

    @NotNull
    private List<MultipartFile> arquivos;

    public AdicionarImagemRequest(List<MultipartFile> arquivos) {
        this.arquivos = arquivos;
    }

    public List<MultipartFile> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<MultipartFile> arquivos) {
        this.arquivos = arquivos;
    }

}
