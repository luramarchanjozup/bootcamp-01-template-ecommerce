package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.Compra;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.ComunicacaoComSistemas;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.OutrosSistemasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TentativaDePagamentoService {

    @Autowired //1
    private ComunicacaoComSistemas comunicacaoComSistemas;

                                                //2                 //3
    public void processaTentativaDePagamento(Compra compra, TentativaDePagamento tentativaDePagamento, String urlBase){

        //4
        if(!tentativaDePagamento.transacaoFoiUmSucesso()){
            System.out.println(compra.prepararFalhaDeCompraParaEmailDoComprador(urlBase));
            return;
        }
                                                        //5
        comunicacaoComSistemas.enviarNotaFiscal(new OutrosSistemasDTO(compra.getId(), compra.retornarIdComprador()));
        comunicacaoComSistemas.enviarRankingVendedores(new OutrosSistemasDTO(compra.getId(), compra.retornarIdVendedor()));
        System.out.println(compra.prepararDetalhesDeCompraParaEmailDoComprador());

    }
}
