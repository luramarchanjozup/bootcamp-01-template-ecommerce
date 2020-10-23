package br.com.ecommerce.cadastroproduto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valor;

    @ManyToOne
    private Produto produto;

    public Caracteristica(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
