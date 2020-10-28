package br.com.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import br.com.mercadolivre.dto.retorno.NotaFiscalRetornoDTO;
import br.com.mercadolivre.dto.retorno.RankingRetornoDTO;
import br.com.mercadolivre.model.Compra;


//Contagem de Pontos - TOTAL:3
//1 - Compra
//1 - RankingRetornoDTO
//1 - NotaFiscalRetornoDTO

@Service
public class EventosCompraServices {

	 @Autowired
	 private JavaMailSender mailSender;
	 
	 
	public void emiteNF (Compra compraconcluidaSucesso) {
		Assert.isTrue(compraconcluidaSucesso.processadaComSucesso(),"Compra nao concluida com sucesso" + compraconcluidaSucesso);
		
		RestTemplate restTemplate = new RestTemplate();
		NotaFiscalRetornoDTO notafiscalretorno = new NotaFiscalRetornoDTO(compraconcluidaSucesso);
		restTemplate.postForEntity("http://localhost:8080/v1/notafiscal",notafiscalretorno, NotaFiscalRetornoDTO.class);
	}
	
	public void emiteRanking (Compra compraconcluidaSucesso) {
		Assert.isTrue(compraconcluidaSucesso.processadaComSucesso(),"Compra nao concluida com sucesso" + compraconcluidaSucesso);
		RestTemplate restTemplate = new RestTemplate();
		RankingRetornoDTO rankingretornodto = new RankingRetornoDTO(compraconcluidaSucesso);
		restTemplate.postForEntity("http://localhost:8080/v1/ranking",rankingretornodto, RankingRetornoDTO.class);
	}
	
	public void enviaEmailSucesso (Compra compraconcluidaSucesso) {
		System.out.println(" -------------------- EMAIL --------------------");
		System.out.println("Obrigado " + compraconcluidaSucesso.getComprador().getLoguin() + "!!");
		System.out.println("Seu pedido para o produto: " + compraconcluidaSucesso.getProdutoEscolhido().getNome() + " foi realizado com sucesso");
		System.out.println(" -----------------------------------------------------");
	}
	
	public void enviaEmailFalhaDesenv (Compra compraErro) {
		System.out.println(" -------------------- EMAIL --------------------");
		System.out.println("Obrigado " + compraErro.getComprador().getLoguin() + "!!");
		System.out.println("Infelizmente seu pagamento não foi concluido com sucesso.");
		System.out.println("Pode fazer o pagamento novamente através do link: " + "/v1/retorno-pagseguro/" + compraErro.getId());
		System.out.println(" -----------------------------------------------------");
	}
	
	public void enviaEmailFalhaProducao (Compra compraErro) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(compraErro.getComprador().getLoguin());
        email.setSubject("Mercado Livre - Pagamento Pendente");
        email.setText("Obrigado " + compraErro.getComprador().getLoguin() + "!!" + "\n" +
        		"Infelizmente seu pagamento não foi concluido com sucesso." + "\n" +
        		"Pode fazer o pagamento novamente através do link: " + "/v1/retorno-pagseguro/" + compraErro.getId());
        mailSender.send(email);
	}
}
