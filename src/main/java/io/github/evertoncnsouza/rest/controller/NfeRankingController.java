package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.rest.dto.NovaCompraNFRequest;
import io.github.evertoncnsouza.rest.dto.RankingNovaCompraRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


//2 PCI's
@RestController
public class NfeRankingController {

    @PostMapping("/notasfiscais")
    public void savenota(@Valid @RequestBody NovaCompraNFRequest request)throws InterruptedException {
        System.out.println("criando NF " +request );
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void saveranking(@RequestBody @Valid RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("Lançando no Ranking a "+request);
        Thread.sleep(150);
    }
}
