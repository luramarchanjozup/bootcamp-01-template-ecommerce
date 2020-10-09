package br.com.ecommerce.cadastroproduto;

import org.springframework.web.multipart.MultipartFile;

public class AdicionarImagemRequest {

    private MultipartFile arquivo;

    public AdicionarImagemRequest(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    public ImagemProduto criaLinkDaImagem(){

        return new ImagemProduto(arquivo.getOriginalFilename());

    }

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
}
