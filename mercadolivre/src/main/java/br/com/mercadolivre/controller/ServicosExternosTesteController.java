package br.com.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.retorno.NotaFiscalRetornoDTO;
import br.com.mercadolivre.dto.retorno.RankingRetornoDTO;

//Contagem de Pontos - TOTAL:2
//1 - NotaFiscalRetornoDTO
//1 - RankingRetornoDTO

@RestController
public class ServicosExternosTesteController {

	
	@PostMapping(value = "/v1/notafiscal")
	public ResponseEntity<?> emiteNoteFiscal(@Valid @RequestBody NotaFiscalRetornoDTO notafiscalretornodto) {
		System.out.println(" -------------------- NOTA FISCAL --------------------");
		System.out.println(notafiscalretornodto.toString());
		System.out.println(" -----------------------------------------------------");
		return new ResponseEntity<>(notafiscalretornodto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/v1/ranking")
	public ResponseEntity<?> emiteRanking(@Valid @RequestBody RankingRetornoDTO rankingretornodto) {
		System.out.println(" -------------------- RANKING --------------------");
		System.out.println(rankingretornodto.toString());
		System.out.println(" -----------------------------------------------------");
		return new ResponseEntity<>(rankingretornodto,HttpStatus.OK);
	}
	
}
