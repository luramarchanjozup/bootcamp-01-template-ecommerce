package br.com.carlos.ecommerce.domain.entity;

import br.com.carlos.ecommerce.api.dto.StatusTransacao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private String idTransacaoGateway;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @NotNull @Valid
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() { }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacaoGateway, @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transacao other = (Transacao) obj;
        if (idTransacaoGateway == null) {
            return other.idTransacaoGateway == null;
        } else return idTransacaoGateway.equals(other.idTransacaoGateway);
    }
}
