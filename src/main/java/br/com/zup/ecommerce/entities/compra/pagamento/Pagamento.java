package br.com.zup.ecommerce.entities.compra.pagamento;

import br.com.zup.ecommerce.entities.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Entity
public class Pagamento {

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
    @OneToOne
    //1
    private Compra compra;

    private LocalDateTime dataTransacao = LocalDateTime.now();

    public Pagamento(@NotBlank String idTransacao, @NotNull StatusTransacaoEnum status, @NotNull @Valid Compra compra) {
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
}
