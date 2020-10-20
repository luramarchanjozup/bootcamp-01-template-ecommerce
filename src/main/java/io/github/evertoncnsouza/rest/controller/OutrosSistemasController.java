package io.github.evertoncnsouza.rest.controller;

import io.github.evertoncnsouza.rest.dto.CompraNFRequest;
import io.github.evertoncnsouza.rest.dto.RankingNovaCompraRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("outraapi")
public class OutrosSistemasController {

    @PostMapping("notasfiscais")
    public void savenota(@Valid @RequestBody CompraNFRequest request)
            throws InterruptedException {
        System.out.println("criando NF"+request);
        Thread.sleep(150);
    }

    @PostMapping("ranking")
    public void rankin(@Valid @RequestBody RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("criando ranking"+request);
        Thread.sleep(150);
    }
}
