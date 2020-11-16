package br.com.ecommerce.finalizacompra;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @complexidade = acoplamento contextual */
    @Enumerated(value = EnumType.STRING)
    private StatusTransacao status;

    private Long idTransacaoGateway;

    private OffsetDateTime instanteCriacao = OffsetDateTime.now();

    /* @complexidade = acoplamento contextual */
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao(){}

    public Transacao(StatusTransacao status, Long idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

}
