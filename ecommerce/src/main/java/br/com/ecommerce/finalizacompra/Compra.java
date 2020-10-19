package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;


@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantidade;

    @Enumerated
    private GatewayPagamento gatewayPagamento;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @Deprecated
    public Compra() {}

    public Compra(Long quantidade, GatewayPagamento gatewayPagamento, Produto produtoEscolhido, Usuario comprador) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produtoEscolhido;
        this.usuario = comprador;
    }

    public Long getId() {
        return id;
    }

    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public void setGatewayPagamento(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }
}
