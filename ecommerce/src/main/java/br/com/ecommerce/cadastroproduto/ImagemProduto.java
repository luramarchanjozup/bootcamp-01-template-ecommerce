package br.com.ecommerce.cadastroproduto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String linkImagem;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto(){};

    public ImagemProduto(String nomeArquivo) {

        //1
        String basePath = "/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/" +
                "ecommerce/src/main/resources/static/imagens";

        //1
        this.linkImagem = basePath + nomeArquivo;

    }

    public String getLinkImagem() {
        return linkImagem;
    }

    public void setLinkImagem(String linkImagem) {
        this.linkImagem = linkImagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
