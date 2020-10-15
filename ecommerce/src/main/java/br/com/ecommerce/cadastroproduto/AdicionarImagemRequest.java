package br.com.ecommerce.cadastroproduto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class AdicionarImagemRequest {

    private List<MultipartFile> arquivos;

    public AdicionarImagemRequest(List<MultipartFile> arquivos) {
        this.arquivos = arquivos;
    }

    public List<MultipartFile> getArquivos() {
        return arquivos;
    }

}
