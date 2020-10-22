package br.com.zup.ecommerce.service.produto;

import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.service.uploader.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Component
public class EnvioImagem {

    @Autowired
    //1
    private Uploader uploader;

    //1
    public Set<String> enviaImagem(ImagensNovoRequest novasImagens) {
        return uploader.enviaImagens(novasImagens.getImagens());
    }

}
