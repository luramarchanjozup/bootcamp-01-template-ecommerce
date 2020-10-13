package com.github.marcoscoutozup.ecommerce.compra.comunicacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ComunicacaoComSistemas", url = "http://localhost:8080")
public interface ComunicacaoComSistemas {

    @PostMapping("/notafiscal")
    void enviarNotaFiscal(@RequestBody OutrosSistemasDTO dto);

    @PostMapping("/rankingvendedores")
    void enviarRankingVendedores(@RequestBody OutrosSistemasDTO dto);
}
