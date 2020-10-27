package br.com.zup.ecommerce.entities.compra.transacao;

import br.com.zup.ecommerce.entities.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Entity
public class Transacao {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String idTransacao;

    @NotNull
    //1
    private StatusTransacaoEnum status;

    @NotNull
    @Valid
    @ManyToOne
    //1
    private Compra compra;

    private LocalDateTime dataTransacao = LocalDateTime.now();

    @Deprecated
    public Transacao(){}

    public Transacao(@NotBlank String idTransacao, @NotNull StatusTransacaoEnum status, @NotNull @Valid Compra compra) {
        this.idTransacao = idTransacao;
        this.status = status;
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusTransacaoEnum getStatus() {
        return status;
    }

    public Compra getCompra() {
        return compra;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacaoEnum.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return idTransacao.equals(transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }
}
