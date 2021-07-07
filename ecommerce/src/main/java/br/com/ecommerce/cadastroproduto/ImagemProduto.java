package br.com.ecommerce.cadastroproduto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "imagem")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String linkImagem;

    /* @complexidade = acoplamento contextual */
    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto(){};

    public ImagemProduto(@NotBlank String linkImagem, Produto produto) {
        this.linkImagem = linkImagem;
        this.produto = produto;
    }


    public String getLinkImagem() {
        return linkImagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
