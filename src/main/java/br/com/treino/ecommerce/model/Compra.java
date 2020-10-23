package br.com.treino.ecommerce.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private @NotNull @Valid Produto produto;
    private @NotNull @Positive int quantidade;
    @ManyToOne
    private @NotNull @Valid Usuario comprador;
    private @NotBlank String meioPagamento;
    private @NotNull String status;

    public Compra(@NotNull @Valid Produto produto, @NotNull @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, @NotBlank String meioPagamento) {
        this.produto = produto;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.meioPagamento = meioPagamento;
        this.status = "INICIADA";
    }

    @Override
    public String toString() {
        return "Compra{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", meioPagamento='" + meioPagamento + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
