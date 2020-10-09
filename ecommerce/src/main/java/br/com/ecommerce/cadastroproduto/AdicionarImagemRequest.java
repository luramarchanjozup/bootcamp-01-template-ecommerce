package br.com.ecommerce.cadastroproduto;

import org.springframework.web.multipart.MultipartFile;

public class AdicionarImagemRequest {

    private MultipartFile arquivo;

    public AdicionarImagemRequest(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
}
