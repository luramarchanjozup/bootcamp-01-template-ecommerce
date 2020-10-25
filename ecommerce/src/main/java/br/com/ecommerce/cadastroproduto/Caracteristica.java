package br.com.ecommerce.cadastroproduto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String valor;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica(){}

    public Caracteristica(String nome, String valor, Produto produto) {
        this.nome = nome;
        this.valor = valor;
        this.produto = produto;
    }

    public Caracteristica(String nome, String valor) {
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
