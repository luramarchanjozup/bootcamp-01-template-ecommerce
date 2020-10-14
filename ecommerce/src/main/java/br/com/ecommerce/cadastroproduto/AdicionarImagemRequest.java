package br.com.ecommerce.cadastroproduto;

import javassist.ByteArrayClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;

public class AdicionarImagemRequest {

    private MultipartFile arquivo;

    public AdicionarImagemRequest(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    public ImagemProduto criaLinkDaImagem(EntityManager entityManager, Long produtoId){

        return new ImagemProduto(arquivo.getOriginalFilename(), entityManager.find(Produto.class, produtoId));

    }

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
}
