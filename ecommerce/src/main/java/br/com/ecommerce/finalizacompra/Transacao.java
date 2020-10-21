package br.com.ecommerce.finalizacompra;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private StatusTransacao status;

    private Long idTransacaoGateway;

    private OffsetDateTime instanteCriacao;

    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao(){}

    public Transacao(StatusTransacao status, Long idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instanteCriacao = OffsetDateTime.now();
        this.compra = compra;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

}
