package br.com.zup.ecommerce.entities.compra;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Contagem de carga intrínseca da classe: 4
 */

@Entity
public class Compra {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @Valid
    @ManyToOne
    //1
    private Produto produto;

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    //1
    private TipoPagamentoEnum tipoPagamento;
    
    @NotNull
    @Valid
    @ManyToOne
    //1
    private Usuario comprador;
    
    @NotNull
    @Enumerated
    //1
    private StatusPagamentoEnum status;

    public Compra(@NotNull @Valid Produto produto, @NotNull @Positive int quantidade, @NotNull TipoPagamentoEnum tipoPagamento, @NotNull @Valid Usuario comprador) {
        this.status = StatusPagamentoEnum.INICIADA;
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipoPagamento = tipoPagamento;
        this.comprador = comprador;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoPagamentoEnum getTipoPagamento() {
        return tipoPagamento;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public StatusPagamentoEnum getStatus() {
        return status;
    }

    public String getLinkPagamento(){
        String host = ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString();
        String linkRetornoPosPagamento = host + "/compras/" + this.id;
        return String.format(this.tipoPagamento.geLink(),
                this.id,
                linkRetornoPosPagamento
        );
    }
}
