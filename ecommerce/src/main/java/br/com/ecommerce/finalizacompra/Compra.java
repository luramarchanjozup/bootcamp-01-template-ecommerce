package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantidade;

    @ManyToOne
    private Produto produtoEscolhido;

    @ManyToOne
    private Usuario comprador;

    public Compra(Long quantidade, Produto produtoEscolhido, Usuario comprador) {
        this.quantidade = quantidade;
        this.produtoEscolhido = produtoEscolhido;
        this.comprador = comprador;
    }

}
