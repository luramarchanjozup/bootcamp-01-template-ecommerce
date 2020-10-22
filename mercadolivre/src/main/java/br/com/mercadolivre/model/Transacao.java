package br.com.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mercadolivre.dto.enums.StatusTransacao;


//Contagem de Pontos - TOTAL:1
//1 - Compra


@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private StatusTransacao status;
	@NotBlank
	private String idTransacaoGateway;
	@NotNull
	private LocalDateTime instante = LocalDateTime.now();
	@ManyToOne
	private @NotNull Compra compra;
	
	
	@Deprecated
	public Transacao() {
	}
	
	public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacaoGateway, @NotNull Compra compra) {
		super();
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.compra = compra;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public String getIdTransacaoGateway() {
		return idTransacaoGateway;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	@Override
	public String toString() {
		return "Transacao [status=" + status + ", idTransacaoGateway=" + idTransacaoGateway + ", instante=" + instante
				+ ", compra=" + compra + "]";
	}
	
	
}
